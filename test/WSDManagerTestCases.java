import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import managers.WSDManager;
import net.sf.extjwnl.data.Synset;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.AruthAPIException;
import exceptions.ErrorCodes;


public class WSDManagerTestCases {

	private final String CONTEXT = "කොළඹ පිටත වටරවුම් අධිවේගී මාර්ගයේ ඉදිකිරීම් සදහා රැගෙන ආ යකඩ බට " +
										"සොරකම්කිරීමේ සිද්ධියකට සම්බන්ධ සැකකරුවන් 6 දෙනෙකු අත්අඩංගුවට ගෙන " +
										"තිබෙන අතරසැකකරුවන් අතර කිරුළපන පොලිස් ස්ථානයට අනුයුක්ත " +
										"සැරයන්වරයෙකුද සිටින බව පැවසේ";
	
	private final String SINHALA_WORD_CORRECT = "බට";
	private final String SINHALA_WORD_WRONG = "බටමුව";
	private final String ENGLISH_WORD = "glass";
	private final String NONSE	= "24TDSFVබටEV";
	
	private static WSDManager wsdManager;
	
	@BeforeClass
	public static void prepare () {
		wsdManager = new WSDManager();
	}
	
	/*
	 * Test if WSDManager.getSense method is working properly
	 */	
	@Test
	public void testGetSense1 () {
		
		try {
			System.out.println("Executing WSDManagerTestCases.testGetSense1");
			
			// should not throw an exception
			Synset sense = wsdManager.getSense(CONTEXT, SINHALA_WORD_CORRECT);
			
			// should not be null
			assertTrue(sense != null);
			System.out.println("Succesfully executed WSDManagerTestCases.testGetSense1");
			
		} catch (AruthAPIException e) {
			System.err.println("Test failed: " + e.getErrorCode() + " " + e.getMessage());
			assertFalse(true);
		}
	}
	
	@Test
	public void testGetSense2 () {
		
		try {
			System.out.println("Executing WSDManagerTestCases.testGetSense2");
			
			// should throw an exception
			@SuppressWarnings(value = { "unused" })
			Synset sense = wsdManager.getSense(CONTEXT, SINHALA_WORD_WRONG);
			
			assertFalse(true);
			System.out.println("Test failed");
			
		} catch (AruthAPIException e) {
			assertTrue(e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND);
			System.out.println("Succesfully executed WSDManagerTestCases.testGetSense2");
		}
	}
	
	@Test
	public void testGetSense3 () {
		
		try {
			System.out.println("Executing WSDManagerTestCases.testGetSense3");
			
			// should throw an exception
			@SuppressWarnings(value = { "unused" })
			Synset sense = wsdManager.getSense(CONTEXT, ENGLISH_WORD);
			
			assertFalse(true);
			System.out.println("Test failed");
			
		} catch (AruthAPIException e) {
			assertTrue(e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND);
			System.out.println("Succesfully executed WSDManagerTestCases.testGetSense3");
		}
	}
	
	@Test
	public void testGetSense4 () {
		
		try {
			System.out.println("Executing WSDManagerTestCases.testGetSense4");
			
			// should throw an exception
			@SuppressWarnings(value = { "unused" })
			Synset sense = wsdManager.getSense(CONTEXT, NONSE);
			
			assertFalse(true);
			System.out.println("Test failed");
			
		} catch (AruthAPIException e) {
			assertTrue(e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND);
			System.out.println("Succesfully executed WSDManagerTestCases.testGetSense4");
		}
	}
	
}
