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

import algorithms.OptimizedLeskV1;
import algorithms.SimplifiedLeskV1;

import dao.WordNetReader;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.list.PointerTargetNode;
import net.sf.extjwnl.data.list.PointerTargetNodeList;

public class WSDManager {

	private static final ALogger logger = Logger.of(WSDManager.class);
	
	/*
	 * Input strings 'context' and 'target'
	 * Output the disambiguated sense of the target word
	 * Implemented only for the noun disambiguation currently
	 */
	public String getSense (String context, String target) {
		//String gloss = getNounSenseUsingSLV1(context, target);
		String gloss = getNounSensesUsingOLV1(context, target);
		String sense = getSenseOfAGloss(gloss);
		
		return sense;
	}
	
	/*
	 * Input strings 'context' and 'target'
	 * Output the disambiguated target word - a noun
	 * Uses Simplified Lesk Algorithm Version 1.0
	 */
	private String getNounSenseUsingSLV1 (String context, String target) {
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
	
	/*
	 * Input strings 'context' and 'target'
	 * Output the disambiguated target word - a noun
	 * Uses Optimized Lesk Algorithm Version 1.0
	 */	
	private String getNounSensesUsingOLV1 (String context, String target) {
		IndexWord word = WordNetReader.getNounAsIndexWord(target);
		List <String> glosses, parentGlosses, childGlosses;
		String sense;
		int senseIndex;
 
		if (word == null) {
			String error = "no match found for noun " + target;
			logger.warn(error);
			return error;
		} 
		
		glosses = getGlosses(word);
		parentGlosses = getParentGlosses(word);
		childGlosses = getChildGosses(word);
		
		try {
			senseIndex = new OptimizedLeskV1().getNounSense(glosses, 
															parentGlosses, 
															childGlosses,
															context, 
															target);
			sense = glosses.get(senseIndex);
			return sense;
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage());
			return null;
		}
	}
	
	@SuppressWarnings("unused")
	private String getVerbSense () {
		
		return "method not implemented yet";
	}
	
	@SuppressWarnings("unused")
	private String getAdjectiveSense () {
		
		return "method not implemented yet";
	}
	
	@SuppressWarnings("unused")
	private String getAdverbSense () {
		
		return "method not implemented yet";
	}
	
	/*
	 * returns a List of glosses for the senses of the IndexWord word
	 */
	private List<String> getGlosses(IndexWord word) {
		List <Synset> synset = word.getSenses();
		List <String> glosses = new ArrayList<>();
		
		for (Synset syn : synset) {
			glosses.add(syn.getGloss());
		}
		
		return glosses;
	}
	
	/*
	 * returns a List of glosses of the senses of the parent of the Indexed word
	 */
	private List<String> getParentGlosses(IndexWord word) {
		List <Synset> synset = word.getSenses();
		List <String> parentGlosses = new ArrayList<>();
		PointerTargetNodeList hypernym;
		
		for (Synset s : synset) {
			hypernym = PointerUtils.getDirectHypernyms(s);
			
			if (hypernym.size() == 0) {
				logger.info("No hypernyms for sense " + s.getGloss() + " in Sinhala WordNet");
				parentGlosses.add("");
			} else {
				// only consider the immidiate synset
				parentGlosses.add(hypernym.get(0).getSynset().getGloss());
			}
		}
		
		return parentGlosses;		
	}
	
	/*
	 * returns a List of glosses of the senses of the child of the Indexed word
	 * note : glosses are converted into the lower case to be compared 
	 */
	private List<String> getChildGosses(IndexWord word) {
		List <Synset> synset = word.getSenses();
		List <String> childGlosses = new ArrayList<>();
		PointerTargetNodeList hyponym;
		
		for (Synset s : synset) {
			hyponym = PointerUtils.getDirectHyponyms(s);
			
			if (hyponym.size() == 0) {
				logger.info("No hyponyms for sense " + s.getGloss() + " in Sinhala WordNet");
				childGlosses.add("");
			} else {
				// only consider the immidiate synset
				childGlosses.add(hyponym.get(0).getSynset().getGloss());
			}
		}
		
		return childGlosses;		
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
