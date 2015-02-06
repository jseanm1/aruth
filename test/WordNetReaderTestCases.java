
import static org.junit.Assert.*;

import net.sf.extjwnl.data.IndexWord;

import org.junit.Test;

import dao.WordNetReader;
import exceptions.AruthAPIException;
import exceptions.ErrorCodes;

public class WordNetReaderTestCases {
	
	private final String SINHALA_WORD_CORRECT = "මුව";
	private final String SINHALA_WORD_WRONG = "මුවමුව";
	private final String ENGLISH_WORD = "glass";
	private final String NONSE	= "24TDSFVමුවEV";
	
	/*
	 * Test if WordNetReader.getNounAsIndexWord method is working properly
	 */
	@Test
	public void testGetNounAsIndexWord1 () {
		try {
			System.out.println("Executing WordNetReaderTestCases.testGetNounAsIndexWord1");
			
			// should not throw an exception
			IndexWord indexWord = WordNetReader.getNounAsIndexWord(SINHALA_WORD_CORRECT);
			
			//should be not null
			assertTrue(indexWord != null);
			System.out.println("Succesfully executed WordNetReaderTestCases.testGetNounAsIndexWord");
			
		} catch (AruthAPIException e) {
			System.err.println("Test failed: " + e.getErrorCode() + " " + e.getMessage());
			assertFalse(true);
		}
	}
	
	@Test
	public void testGetNounAsIndexWord2 () {
		try {
			System.out.println("Executing WordNetReaderTestCases.testGetNounAsIndexWord2");
			
			// should throw an exception
			@SuppressWarnings(value = { "unused" })
			IndexWord indexWord = WordNetReader.getNounAsIndexWord(SINHALA_WORD_WRONG);
			
			System.err.println("Test failed");
			assertFalse(true);
			
		} catch (AruthAPIException e) {
			assertTrue(e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND);
			System.out.println("Succesfully executed WordNetReaderTestCases.testGetNounAsIndexWord");
		}
	}
	
	@Test
	public void testGetNounAsIndexWord3 () {
		try {
			System.out.println("Executing WordNetReaderTestCases.testGetNounAsIndexWord3");
			
			// should throw an exception
			@SuppressWarnings(value = { "unused" })
			IndexWord indexWord = WordNetReader.getNounAsIndexWord(ENGLISH_WORD);
			
			System.err.println("Test failed");
			assertFalse(true);
			
		} catch (AruthAPIException e) {
			assertTrue(e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND);
			System.out.println("Succesfully executed WordNetReaderTestCases.testGetNounAsIndexWord");
		}
	}
	
	@Test
	public void testGetNounAsIndexWord4 () {
		try {
			System.out.println("Executing WordNetReaderTestCases.testGetNounAsIndexWord3");
			
			// should throw an exception
			@SuppressWarnings(value = { "unused" })
			IndexWord indexWord = WordNetReader.getNounAsIndexWord(NONSE);
			
			System.err.println("Test failed");
			assertFalse(true);
			
		} catch (AruthAPIException e) {
			assertTrue(e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND);
			System.out.println("Succesfully executed WordNetReaderTestCases.testGetNounAsIndexWord");
		}
	}

}
