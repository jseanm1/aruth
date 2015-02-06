import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import managers.SenseManager;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.AruthAPIException;
import exceptions.ErrorCodes;


public class SenseManagerTestCases {
	
	private final String SINHALA_WORD_CORRECT = "මුව";
	private final String SINHALA_WORD_WRONG = "මුවමුව";
	private final String ENGLISH_WORD = "glass";
	private final String NONSE	= "24TDSFVමුවEV";
	
	private static SenseManager senseManager;
	
	@BeforeClass
	public static void prepare () {
		senseManager = new SenseManager();
	}
	/*
	 * Tests if SenseManager.getAllSenses is working properly
	 */
	@Test
	public void testGetAllSenses () {
		test1(SINHALA_WORD_CORRECT);
		test2(SINHALA_WORD_WRONG);
		test2(ENGLISH_WORD);
		test2(NONSE);
	}
	
	public void test1 (String noun) {
		try {
			System.out.println("Executing SenseManagerTestCase.test1(" + noun + ")");
			
			// should not throw an exception
			List<String> senses = senseManager.getAllSenses(noun);
			
			// should not be null
			assertTrue(senses != null);
			
			// should be of size larger than zero
			assertTrue(senses.size() > 0);
			System.out.println("Succesfuylly executed SenseManagerTestCase.test1(" + noun + ")");
			
		} catch (AruthAPIException e) {
			System.err.println("Test failed: " + e.getErrorCode() + " " + e.getMessage());
			assertFalse(true);
			
		}
	}
	
	public void test2 (String noun) {
		try {
			System.out.println("Executing SenseManagerTestCase.test1(" + noun + ")");
		
			//should throw an exception	
			@SuppressWarnings(value = { "unused" })
			List<String> senses = senseManager.getAllSenses(noun);
			
			System.err.println("Test failed");
			assertFalse(true);
			
		} catch (AruthAPIException e) {
			assertTrue(e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND);
			System.out.println("Succesfuylly executed SenseManagerTestCase.test1(" + noun + ")");
			
		}		
	}
}
