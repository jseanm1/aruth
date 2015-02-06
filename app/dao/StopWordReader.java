/*
 * This class is used to read the stop word list from data directory
 */
package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import exceptions.AruthAPIException;
import exceptions.ErrorCodes;

import play.Logger;
import play.Logger.ALogger;

public class StopWordReader {
	
	private static final ALogger logger = Logger.of(StopWordReader.class);
	
	private static final String STOPWORD_PATH = "data/stopwords.wsd";
	
	public List<String> getStopWords () throws AruthAPIException {
		
		FileInputStream fis;
		List<String> stopwords = new ArrayList<String>();
		
		try {
			fis = new FileInputStream(new File(STOPWORD_PATH));
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			
			String s = br.readLine();
			
			while (s != null) {
				stopwords.add(s);
				s = br.readLine();
			}
			
			return stopwords;
			
		} catch (FileNotFoundException e) {	
			
			String errorMessage = "Cannot open file at " + STOPWORD_PATH;			
			logger.warn(errorMessage);
			
			throw new AruthAPIException(ErrorCodes.CANNOT_OPEN_FILE, errorMessage, null);
			
		} catch (IOException e) {
			
			String errorMessage = "Cannot read file at " + STOPWORD_PATH;			
			logger.warn(errorMessage);
			
			throw new AruthAPIException(ErrorCodes.CANNOT_READ_FILE, errorMessage, null);
		}		
		
	}

}
