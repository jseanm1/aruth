/*
 * Version 1.0 of the optimization of Simplified Lesk algorithm
 * This class should be instantiated in the managers layer
 * All input to be provided by managers layer
 * All output to be provided to managers layer
 */
package algorithms;

import java.io.IOException;
import java.util.List;

import utils.LeskPreprocessor;

public class OptimizedLeskV1 {

	/*
	 * Actual implementation of the Simplified Lesk algorithm Input glosses and
	 * the context Output relevant sense
	 */

	private List<String> pContext;
	private List<String> pGlosses;
	private int size;
	private static final float A = 0.5f;

	public int getNounSense(List<String> glosses, String context, String target) throws IOException {
		int maxIndex = 0;
		float maxvalue = 0;
		
		// preprocess context
		pContext = LeskPreprocessor.preprocessContext(context);

		// preprocess glosses
		pGlosses = LeskPreprocessor.preprocessGlosses(glosses);
		
		size = pGlosses.size();

		float primaryCount[] = getPrimaryCount();
		
		float secondaryCount[] = getSecondaryCount();	
		
		float finalCount[] = new float[size];
		
		for (int i=0; i<size; i++) {
			finalCount[i] = primaryCount[i] + A * secondaryCount[i];
		}
		
		for (int i=0; i<size; i++) {
			if (finalCount[i]>maxvalue) {
				maxvalue = finalCount[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	private float[] getPrimaryCount () {
		
		float primaryCount[] = new float[size];
		
		for (int i = 0; i < size; i++) {
			primaryCount[i] = 0;
		}

		for (int i = 0; i < size; i++) {
			for (String word : pGlosses.get(i).split(" ")) {
				if (pContext.contains(word)) {
					primaryCount[i]++;
				}
			}
		}
		
		return primaryCount;
	}
	
	private float[] getSecondaryCount() {
		// TODO Auto-generated method stub
		return null;
	}
}
