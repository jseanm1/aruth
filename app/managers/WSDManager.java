/*
 * This class handles word sense disambiguation
 * It requests data from the dao layer and requests algorithms to be performed from the algorithm layer
 * Returns the sense of the target word according to the context to the controller layer
 */
package managers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import play.Logger;
import play.Logger.ALogger;

import algorithms.SimplifiedLeskV1;

import dao.WordNetReader;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.Synset;

public class WSDManager {

	private static final ALogger logger = Logger.of(WSDManager.class);
	/*
	 * Input strings 'context' and 'target'
	 * Output the disambiguated sense of the target word
	 * Implemented only for the noun disambiguation currently
	 */
	public String getSense (String context, String target) {
		String gloss = getNounSense(context, target);
		String sense=getSenseOfAGloss(gloss); 
		
		return sense;
	}
	
	/*
	 * Input strings 'context' and 'target'
	 * Output the disambiguated target word - a noun
	 */
	private String getNounSense (String context, String target) {
		IndexWord word = WordNetReader.getNounAsIndexWord(target);
		List <String> glosses;
		String sense;
		int senseIndex;
		
		if (word == null) {
			String error = "no match found for noun " + target;
			logger.warn(error);
			return error;
		} 
		
		glosses = getGlosses(word);
		
		try {
			senseIndex = new SimplifiedLeskV1().getNounSense(glosses, context, target);
			sense = glosses.get(senseIndex);
			return sense;
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage());
			return null;
		}
		
		
	}
	
	private String getVerbSense () {
		
		return "method not implemented yet";
	}
	
	private String getAdjectiveSense () {
		
		return "method not implemented yet";
	}
	
	private String getAdverbSense () {
		
		return "method not implemented yet";
	}
	
	/*
	 * returns a List of glosses for the senses of the IndexWord word
	 * note : glosses are converted into the lower case to be compared
	 */
	private List<String> getGlosses(IndexWord word) {
		List <Synset> synset = word.getSenses();
		List <String> glosses = new ArrayList<>();
		
		for (Synset syn : synset) {
			glosses.add(syn.getGloss());
		}
		
		return glosses;
	}
	
	private String[] devideGloss(String gloss)
	{
		String[] string=gloss.split("\\|");
		return string;
		
	}
	
	private String getSenseOfAGloss(String givenGloss)
	{
		String[] gloss=devideGloss(givenGloss);
		String sense=gloss[0];
		return sense;
		
	}
	
	private String getExamplesOfAGloss(String givenGloss)
	{
		String[] gloss=devideGloss(givenGloss);
		String examples=gloss[1];
		return examples;
	} 
}
