import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



import managers.SampleManager;
import dao.WordNetReader;
import exceptions.AruthAPIException;
import net.sf.extjwnl.data.IndexWord;


import org.junit.BeforeClass;
import org.junit.Test;




public class SampleManagerTestCases {
	private final String SINHALA_WORD1 = " අලි";
private static SampleManager sampleManager;
	
	@BeforeClass
	public static void prepare () {
		sampleManager = new SampleManager();
	}

	/*
	 * Tests if SampleManager.getNoun is working properly
	 */
	@Test
	public void testgetNoun () {
		try {
			System.out.println("Executing SamleManagerTestCase.testgetNoun");
			
			// should not throw an exception
			String lemma=sampleManager.getNoun(SINHALA_WORD1);
			System.out.println(lemma);
			
			// should not be null
			assertTrue(lemma != null);
			
			// should be of size larger than zero
			//assertTrue(lemma.equals("මුවන්"));
		
			
		} catch (AruthAPIException e) {
			System.err.println("Test failed: " + e.getErrorCode() + " " + e.getMessage());
			assertFalse(true);
			
		}
	}
}
