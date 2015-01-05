/*
 * This class handles word sense disambiguation
 * It requests data from the dao layer and requests algorithms to be performed from the algorithm layer
 * Returns the sense of the target word according to the context to the controller layer
 */
package managers;

import java.util.ArrayList;
import java.util.List;

import dao.WordNetReader;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.Synset;

public class WSDManager {

	/*
	 * Input strings 'context' and 'target'
	 * Output the disambiguated sense of the target word
	 * Implemented only for the noun disambiguation currently
	 */
	public String getSense (String context, String target) {
		context = context.toLowerCase();
		target = target.toLowerCase();
		
		String sense = getNounSense(context, target);
		
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
		
		if (word == null) {
			String error = "no match found for noun " + target;
			System.out.println(error);
			return error;
		} 
		
		glosses = getGlosses(word);
		
		sense = getSenseByLesk(glosses, context);
		
		return "method not implemented yet";
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
			glosses.add(syn.getGloss().toLowerCase());
		}
		return glosses;
	}
	
	/*
	 * Actual implementation of the Simplified Lesk algorithm
	 * Input glosses and the context
	 * Output relevant sense
	 */
	private String getSenseByLesk(List<String> glosses, String context) {
		// TODO Auto-generated method stub
		return null;
	}
}
