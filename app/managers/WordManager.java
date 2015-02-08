/*
 * This class handles any queries about words
 */
package managers;

import java.util.ArrayList;
import java.util.List;

import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.Word;
import dao.WordNetReader;
import exceptions.AruthAPIException;

import play.Logger;
import play.Logger.ALogger;

public class WordManager {
	
	private static final ALogger logger = Logger.of(WordManager.class);
	
	/*
	 * Input String 'word' and 'sense'
	 * Output List<String> all the words that has that sense
	 * Implemented only for nouns
	 */
	public List<String> getAllWordsForASense (long offset) throws AruthAPIException {
		logger.info("All words for sense " + offset + " requested");
		
		List<Word> words = getAllNounsForASense(offset);
		List<String> wordList = new ArrayList<String>();
		
		for (Word w : words) {
			wordList.add(w.getLemma());
		}
		
		return wordList;
	}
	
	private List<Word> getAllNounsForASense (long offset) throws AruthAPIException {
		Synset synset = WordNetReader.getSynset(offset);
		
		List<Word> words = synset.getWords();
		
		return words;
	}
}
