/*
 * Version 1.0 of Simplified Lesk algorithm
 * This class should be instantiated in the managers layer
 * All input to be provided by managers layer
 * All output to be provided to managers layer
 */
package algorithms;

import java.util.List;

import exceptions.AruthAPIException;

import utils.LeskPreprocessor;

public class SimplifiedLeskV1 {

	/*
	 * Actual implementation of the Simplified Lesk algorithm
	 * Input glosses and the context
	 * Output relevant sense
	 */
	
	private List<String> pContext;
	private List<String> pGlosses;
	
	public int getNounSense (List<String> glosses, String context, String target) 
			throws AruthAPIException {
		// preprocess context
		pContext = LeskPreprocessor.preprocessContext(context);
		
		// preprocess glosses
		pGlosses = LeskPreprocessor.preprocessGlosses(glosses);
		
		int size = pGlosses.size();
		int overLapCount[] = new int[size];
		int maxIndex = 0;
		int maxvalue = 0;
		
		for(int i=0; i<size; i++) {
			overLapCount[i] = 0;
		}
		
		for(int i=0; i<size; i++) {
			for (String word : pGlosses.get(i).split(" ")) {
				if (pContext.contains(word)) {
					overLapCount[i]++;
				}
			}
		}		
		
		for (int i=0; i<size; i++) {
			if (overLapCount[i]>maxvalue) {
				maxvalue = overLapCount[i];
				maxIndex = i;
			}
		}
		
		return maxIndex;
	}
	
}
