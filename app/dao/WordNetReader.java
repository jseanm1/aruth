/*
 * Read WordNet using this class
 * This class should be instantiated in the managers layer
 * All output to be provided to managers layer
 */
package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.dictionary.Dictionary;

public class WordNetReader {

	private final static String WORDNETPATH = "conf/file_properties.xml";

	/*
	 * Input a noun
	 * Will return the IndexWord from the Sinhala WordNet for that particular noun
	 * Will return null if no IndexWord is found
	 */
	public static IndexWord getNounAsIndexWord (String noun) {
		Dictionary dictionary = getDictionary();
		IndexWord word = null;
		
		try {
			word = dictionary.getIndexWord(POS.NOUN, noun);
			System.out.println("word done");
		} catch (JWNLException e) {
			System.out.println("app.dao.WordNetReader.getNounAsIndexWord() : JWNLException");
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
			System.out.println("input stream done");
			dictionary = Dictionary.getInstance(inputStream);
			System.out.println("dictionary done");
		} catch (FileNotFoundException e) {
			System.out.println("app.dao.WordNetReader.getDictionary() : FileNotFoundException");
		} catch (JWNLException e) {
			System.out.println("app.dao.WordNetReader.getDictionary() : JWNLException");
		}
		
		return dictionary;
	}
}
