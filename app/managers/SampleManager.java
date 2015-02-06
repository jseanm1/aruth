/*
 * This is just for developer testing.
 * Should not implement anything in this class that is expected to be deployed
 */
package managers;

import dao.WordNetReader;
import exceptions.AruthAPIException;
import net.sf.extjwnl.data.IndexWord;

public class SampleManager {

	public String getNoun (String noun) throws AruthAPIException {
		
		IndexWord iw = WordNetReader.getNounAsIndexWord(noun);
		
		return iw.getLemma();
	}
}
