/*
 * Read WordNet using this class
 * This class should be instantiated in the managers layer
 * All output to be provided to managers layer
 */
package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import exceptions.AruthAPIException;
import exceptions.ErrorCodes;

import play.Logger;
import play.Logger.ALogger;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.dictionary.Dictionary;

public class WordNetReader {
	
	private static final ALogger logger = Logger.of(WordNetReader.class);
	private final static String WORDNET_PATH = "conf/file_properties.xml";

	/*
	 * Input a noun
	 * Will return the IndexWord from the Sinhala WordNet for that particular noun
	 * Will return null if no IndexWord is found
	 */
	public static IndexWord getNounAsIndexWord (String noun) throws AruthAPIException {
		logger.info("Request to retrieve " + noun + " from Sinhala WordNet");
		
		Dictionary dictionary = getDictionary();
		IndexWord word = null;
		
		try {
			word = dictionary.getIndexWord(POS.NOUN, noun);	
			
		} catch (JWNLException e) {
			
			String errorMessage = "Exception occured trying to read WordNet :" + e.getLocalizedMessage();
			logger.warn(errorMessage);
			
			throw new AruthAPIException(ErrorCodes.CANNOT_READ_DICTIONARY, errorMessage, null);
		}	
		
		if (word == null) {
			
			String errorMessage = "Target word " + noun + " not found in the WordNet";
			logger.warn(errorMessage);
			
			throw new AruthAPIException(ErrorCodes.WORD_NOT_FOUND, errorMessage, null);			
		} else {
			return word;
		}
	}
	
	/*
	 * Input the offset
	 * Will return the synset at that offset 
	 */
	public static Synset getSynset (long offset) throws AruthAPIException {
		Dictionary dictionary = getDictionary();
		Synset synset = null;
		try {
			synset  = dictionary.getSynsetAt(POS.NOUN, offset);
			
		} catch (JWNLException e) {
			
			String errorMessage = "Exception occured trying to read WordNet " + e.getLocalizedMessage();
			logger.warn(errorMessage);
			
			throw new AruthAPIException(ErrorCodes.CANNOT_READ_FILE, errorMessage, null);
		}	
		
		if (synset == null) {
			
			String errorMessage = "Failed to retrieve synset " + offset;
			logger.warn(errorMessage);
			
			throw new AruthAPIException(ErrorCodes.SENSE_NOT_FOUND, errorMessage, null);
		}
		
		return synset;
		
	}
	
	/*
	 * Create a Dictionary and return it
	 */
	private static Dictionary getDictionary() throws AruthAPIException {
		FileInputStream inputStream;
		Dictionary dictionary = null;
		
		try {
			inputStream = new FileInputStream(WORDNET_PATH);
			dictionary = Dictionary.getInstance(inputStream);
			
			return dictionary;
			
		} catch (FileNotFoundException e) {
			
			String errorMessage = "Cannot open file at " + WORDNET_PATH;		
			logger.warn(errorMessage);
			
			throw new AruthAPIException(ErrorCodes.CANNOT_OPEN_FILE, errorMessage, null);
			
		} catch (JWNLException e) {
			
			String errorMessage = "Cannot create dictionary instance";
			logger.warn(errorMessage);
			
			throw new AruthAPIException(ErrorCodes.CANNOT_CREATE_DICTIONARY, errorMessage, null);
		}
		
		
	}
}
