/*
 * This class handles word sense disambiguation
 * It requests data from the dao layer and requests algorithms to be performed from the algorithm layer
 * Returns the sense of the target word according to the context to the controller layer
 */
package managers;

import java.util.ArrayList;
import java.util.List;

import play.Logger;
import play.Logger.ALogger;

import algorithms.OptimizedLeskV1;
import algorithms.SimplifiedLeskV1;

import dao.WordNetReader;
import exceptions.AruthAPIException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.list.PointerTargetNodeList;

public class WSDManager {

	private static final ALogger logger = Logger.of(WSDManager.class);
	
	/*
	 * Input strings 'context' and 'target'
	 * Output the disambiguated sense of the target word (the entire gloss)
	 * Implemented only for the noun disambiguation currently
	 */
	public Synset getSense (String context, String target) throws AruthAPIException {
		//String gloss = getNounSenseUsingSLV1(context, target);
		Synset sense = getNounSensesUsingOLV1(context, target);
		
		/*
		 * sending the entire gloss.
		 */
		//String sense = getSenseOfAGloss(gloss);
		
		return sense;
	}
	
	/*
	 * Input strings 'context' and 'target'
	 * Output the disambiguated target word - a noun
	 * Uses Simplified Lesk Algorithm Version 1.0
	 */
	@SuppressWarnings(value = { "unused" })
	private Synset getNounSenseUsingSLV1 (String context, String target) throws AruthAPIException {
		IndexWord word = WordNetReader.getNounAsIndexWord(target);
		List <String> glosses;
		int senseIndex;
		List<Synset> senses;
		Synset sense = null;
		
		senses = word.getSenses();
		glosses = getGlosses(word);
				
		senseIndex = new SimplifiedLeskV1().getNounSense(glosses, context, target);
		
		for (Synset s : senses) {
			if (s.getGloss().equals(glosses.get(senseIndex))) {
				
				sense = s;
				
				break;				
			}			
		}
		
		return sense;
	}
	
	/*
	 * Input strings 'context' and 'target'
	 * Output the disambiguated target word - a noun
	 * Uses Optimized Lesk Algorithm Version 1.0
	 */	
	private Synset getNounSensesUsingOLV1 (String context, String target) throws AruthAPIException {
		IndexWord word = WordNetReader.getNounAsIndexWord(target);
		List <String> glosses, parentGlosses, childGlosses;
		int senseIndex;
		List<Synset> senses;
		Synset sense = null;
 
		senses = word.getSenses();
		glosses = getGlosses(word);
		parentGlosses = getParentGlosses(word);
		childGlosses = getChildGosses(word);
		
		senseIndex = new OptimizedLeskV1().getNounSense(glosses, 
														parentGlosses, 
														childGlosses,
														context, 
														target);
		for (Synset s : senses) {
			if (s.getGloss().equals(glosses.get(senseIndex))) {
				
				sense = s;
				
				break;				
			}			
		}
		
		return sense;
		
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
			System.out.println(syn.getGloss());
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
				// only consider the immediate synset
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
				// only consider the immediate synset
				childGlosses.add(hyponym.get(0).getSynset().getGloss());
			}
		}
		
		return childGlosses;		
	}
	
	private String[] divideGloss(String gloss)
	{
		String[] string=gloss.split("\\|");
		return string;		
	}
	
	@SuppressWarnings(value = { "unused" })
	private String getSenseOfAGloss(String givenGloss)
	{
		String[] gloss=divideGloss(givenGloss);
		String sense=gloss[0];
		return sense;		
	}
	
	@SuppressWarnings(value = { "unused" })
	private String getExamplesOfAGloss(String givenGloss)
	{
		String[] gloss=divideGloss(givenGloss);
		String examples=gloss[1];
		return examples;
	} 
}
