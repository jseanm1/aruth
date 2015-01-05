/*
 * This is just for developer testing.
 * Should not implement anything in this class that is expected to be deployed
 */
package managers;

import dao.WordNetReader;
import net.sf.extjwnl.data.IndexWord;

public class SampleManager {

	public String getNoun (String noun) {
		
		IndexWord iw = WordNetReader.getNounAsIndexWord(noun);
		
		if (iw == null) {
			return "Error! Word " + noun + "not found";
		} else {
			return iw.getLemma();
		}
	}
}
