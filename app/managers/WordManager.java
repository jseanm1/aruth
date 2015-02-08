/*
 * This class handles any queries about words
 */
package managers;

import java.util.ArrayList;
import java.util.List;

import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.Word;
import dao.WordNetReader;
import exceptions.AruthAPIException;
import exceptions.ErrorCodes;

import play.Logger;
import play.Logger.ALogger;

public class WordManager {
	
	private static final ALogger logger = Logger.of(WordManager.class);
	
	/*
	 * Input String 'word' and 'sense'
	 * Output List<String> all the words that has that sense
	 * Implemented only for nouns
	 */
	public List<String> getAllWordsForASense (String word, String sense) throws AruthAPIException {
		logger.info("All words for sense " + sense + " requested");
		
		List<Word> words = getAllNounsForASense(word, sense);
		List<String> wordList = new ArrayList<String>();
		
		for (Word w : words) {
			wordList.add(w.getLemma());
		}
		
		return wordList;
	}
	
	private List<Word> getAllNounsForASense (String word, String sense) throws AruthAPIException {
		IndexWord iWord = WordNetReader.getNounAsIndexWord(word);
		List<Synset> synsetList = iWord.getSenses();
		Synset synset = null;
		List<Word> words;
		
		for(Synset s : synsetList) {
			if (s.getGloss().equals(sense)) {
				synset = s;
				break;
			}
		}
		
		if (synset == null) {
			String errorMessage = "Sense " + sense + " not found in wordnet";
			logger.warn(errorMessage);
			
			throw new AruthAPIException(ErrorCodes.SENSE_NOT_FOUND, errorMessage, null);
			
		} else {		
			
			words = synset.getWords();
			
			return words;
		}		
	}
}
