import static org.junit.Assert.*;

import java.util.List;

import managers.WordManager;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.AruthAPIException;

public class WordManagerTestCases {

	
	private final long OFFSET =41311;
	private final long WRONG_OFFSET=678495;
	
	private static WordManager wordManager;
	
	@BeforeClass
	public static void prepare () {
		wordManager = new  WordManager();
	}
	/*
	 * Tests if WordManager.getAllWordsForASense is working properly
	 */
	@Test
	public void testGetAllWordsForASense1 () {
		try {
			System.out.println("Executing WordManager.getAllWordsForASense1");
			
			// should not throw an exception
			List<String>  wordList =wordManager.getAllWordsForASense(OFFSET);
			
			// should not be null
			assertTrue( wordList != null);
			
			// should be of size larger than zero
			assertTrue( wordList.size() > 0);
			System.out.println("Succesfuylly executed WordManager.getAllWordsForASense1");
			
		} catch (AruthAPIException e) {
			System.err.println("Test failed: " + e.getErrorCode() + " " + e.getMessage());
			assertFalse(true);
			
			
		}
		
		
	}
	@Test
	public void tsetGetAllWordsForASense2 () {
		try {
			System.out.println("Executing WordManager.getAllWordsForASense2");
			
			//should throw an exception	
			@SuppressWarnings(value = { "unused" })
			List<String> senses =wordManager.getAllWordsForASense(WRONG_OFFSET);
			
			System.err.println("Test failed");
			assertFalse(true);
			
		} catch (AruthAPIException e) {
			
			System.out.println("Succesfuylly executed WordManager.getAllWordsForASense2");
			
		}
	}
}
