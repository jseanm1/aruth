
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dao.StopWordReader;
import exceptions.AruthAPIException;

public class StopWordReaderTestCases {

	/*
	 * Test if StopWordReader.getStopWords method is working properly  
	 */
	@Test
	public void testGetStopWords () {
		
		try {
			System.out.println("Executing StopWOrdReaderTestCases.testGetStopWords");
			
			// should not throw an exception
			List<String> stopWords = new StopWordReader().getStopWords();
			
			//should not be null
			assertTrue(stopWords!=null);
			
			//should not be empty
			assertTrue(stopWords.size()>0);
			System.out.println("Succesfully executed StopWOrdReaderTestCases.testGetStopWords");
			
		} catch (AruthAPIException e) {
			System.err.println("Test failed: " + e.getErrorCode() + " " + e.getMessage());
			assertFalse(true);
		}
	}
}
