/*
 * This is the preprocessor developed for the Lesk Algorithm
 * Uses a list of stop words
 */
package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dao.StopWordReader;
import exceptions.AruthAPIException;

public class LeskPreprocessor {

	public static List<String> preprocessContext (String context) throws AruthAPIException {
		// get stop words
		List<String> stopWords = new StopWordReader().getStopWords();
		
		// replace other characters and get the user context as a list of Strings
		List<String> pContextTemp = Arrays.asList(context.replaceAll("[\\[\\]|\".,]", " ").split(" "));
		List<String> pContext = new ArrayList<String>();
		
		for (String word : pContextTemp) {
			if (!stopWords.contains(word)) {
				pContext.add(word);
			}
		}
		
		return pContext;
	}
	
	public static List<String> preprocessGlosses (List<String> glosses) {
		// replace other characters and get the glosses as a list of Strings
		List<String> pGlosses = new ArrayList<String>();
		
		for (String gloss : glosses) {
			pGlosses.add(gloss.trim().replaceAll(" +", " ").replaceAll("[|,\"]", " ").trim().replaceAll(" +", " "));
		}
		
		return pGlosses;
	}
	
}
