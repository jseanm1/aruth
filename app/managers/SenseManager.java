/*
 * This class handles any queries about word senses
 */
package managers;

import java.util.ArrayList;
import java.util.List;

import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.Synset;
import dao.WordNetReader;
import exceptions.AruthAPIException;

import play.Logger;
import play.Logger.ALogger;

public class SenseManager {
	
	private static final ALogger logger = Logger.of(SenseManager.class);
	
	/*
	 * Input string 'target'
	 * Output all the senses available in the WordNet
	 * Implemented only for nouns
	 */
	public List<String> getAllSenses (String target) throws AruthAPIException {
		List <String> senses = getNounSenses(target);
		
		return senses;
	}
	
	/*
	 * Input string 'target'
	 * Output all the senses available in the WordNet for the noun
	 */
	private List<String> getNounSenses (String target) throws AruthAPIException {
		logger.info("Senses requested for "+target);
		
		IndexWord word = WordNetReader.getNounAsIndexWord(target);
		
		List <Synset> synset = word.getSenses();
		List<String> glosses = new ArrayList<String>();
		
		for (Synset syn : synset) {
			glosses.add(syn.getGloss());
		}
		
		return glosses;
	}

}
