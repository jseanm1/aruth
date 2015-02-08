import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import managers.SenseManager;
import net.sf.extjwnl.data.Synset;

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
	public void testGetAllSenses1 () {
		try {
			System.out.println("Executing SenseManagerTestCase.testGetAllSesnses1");
			
			// should not throw an exception
			List<Synset> senses = senseManager.getAllSenses(SINHALA_WORD_CORRECT);
			
			// should not be null
			assertTrue(senses != null);
			
			// should be of size larger than zero
			assertTrue(senses.size() > 0);
			System.out.println("Succesfuylly executed SenseManagerTestCase.testGetAllSenses1");
			
		} catch (AruthAPIException e) {
			System.err.println("Test failed: " + e.getErrorCode() + " " + e.getMessage());
			assertFalse(true);
			
		}
	}
	
	@Test
	public void testGetAllSenses2 () {
		try {
			System.out.println("Executing SenseManagerTestCase.testGetAllSenses2");
		
			//should throw an exception	
			@SuppressWarnings(value = { "unused" })
			List<Synset> senses = senseManager.getAllSenses(SINHALA_WORD_WRONG);
			
			System.err.println("Test failed");
			assertFalse(true);
			
		} catch (AruthAPIException e) {
			assertTrue(e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND);
			System.out.println("Succesfuylly executed SenseManagerTestCase.testGetAllSenses2");
			
		}		
	}
	
	@Test
	public void testGetAllSenses3 () {
		try {
			System.out.println("Executing SenseManagerTestCase.testGetAllSenses3");
		
			//should throw an exception	
			@SuppressWarnings(value = { "unused" })
			List<Synset> senses = senseManager.getAllSenses(ENGLISH_WORD);
			
			System.err.println("Test failed");
			assertFalse(true);
			
		} catch (AruthAPIException e) {
			assertTrue(e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND);
			System.out.println("Succesfuylly executed SenseManagerTestCase.testGetAllSenses3");
			
		}		
	}
	
	@Test
	public void testGetAllSenses4 () {
		try {
			System.out.println("Executing SenseManagerTestCase.testGetAllSenses4");
		
			//should throw an exception	
			@SuppressWarnings(value = { "unused" })
			List<Synset> senses = senseManager.getAllSenses(NONSE);
			
			System.err.println("Test failed");
			assertFalse(true);
			
		} catch (AruthAPIException e) {
			assertTrue(e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND);
			System.out.println("Succesfuylly executed SenseManagerTestCase.testGetAllSenses4");
			
		}		
	}
}
