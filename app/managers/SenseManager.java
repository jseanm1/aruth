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
	
	private List<Synset> senses;
	
	public SenseManager () {
		senses = new ArrayList<Synset>();
	}
	/*
	 * Input string 'target'
	 * Output all the senses available in the WordNet
	 * Implemented only for nouns
	 */
	public List<Synset> getAllSenses (String target) throws AruthAPIException {
		List <Synset> senses = getNounSenses(target);
		
		return senses;
	}
	
	/*
	 * Input string 'target'
	 * Output all the senses available in the WordNet for the noun
	 */
	private List<Synset> getNounSenses (String target) throws AruthAPIException {
		logger.info("Senses requested for "+target);
		
		IndexWord word = WordNetReader.getNounAsIndexWord(target);
		
		senses = word.getSenses();
				
		return senses;
	}

}
