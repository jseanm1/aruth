/*
 * Read WordNet using this class
 * This class should be instantiated in the managers layer
 * All output to be provided to managers layer
 */
package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import play.Logger;
import play.Logger.ALogger;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.dictionary.Dictionary;

public class WordNetReader {
	
	private static final ALogger logger = Logger.of(WordNetReader.class);
	private final static String WORDNETPATH = "conf/file_properties.xml";

	/*
	 * Input a noun
	 * Will return the IndexWord from the Sinhala WordNet for that particular noun
	 * Will return null if no IndexWord is found
	 */
	public static IndexWord getNounAsIndexWord (String noun) {
		logger.info("Request to retrieve " + noun + " from Sinhala WordNet");
		
		Dictionary dictionary = getDictionary();
		IndexWord word = null;
		
		try {
			word = dictionary.getIndexWord(POS.NOUN, noun);	
		} catch (JWNLException e) {
			logger.error(e.getLocalizedMessage());
		}
		
		return word;
	}
	
	/*
	 * Create a Dictionary and return it
	 */
	private static Dictionary getDictionary() {
		FileInputStream inputStream;
		Dictionary dictionary = null;
		
		try {
			inputStream = new FileInputStream(WORDNETPATH);
			dictionary = Dictionary.getInstance(inputStream);
		} catch (FileNotFoundException e) {
			logger.error(e.getLocalizedMessage());
		} catch (JWNLException e) {
			logger.error(e.getLocalizedMessage());
		}
		
		return dictionary;
	}
}
