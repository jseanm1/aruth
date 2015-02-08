/*
 * Requests for words from WordNet are handled by this class
 */
package controllers;

import java.util.List;

import managers.WordManager;

import com.fasterxml.jackson.databind.JsonNode;

import exceptions.AruthAPIException;
import exceptions.ErrorCodes;

import play.Logger;
import play.Logger.ALogger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class WordController extends Controller {

	private static final ALogger logger = Logger.of(WordController.class);
	
	/*
	 * /getAllWordsForASense
	 * This method will accept a json object which has two attributes 'word' and 'sense'
	 * The attribute values will be passed to the WordManager and the all words for that sense will
	 * be returned
	 */
	public static Result getAllWordsForASense () {
		long offset = -1;
		WordManager wordManager = new WordManager();
		List<String> words;
		
		JsonNode json = request().body().asJson();
		
		if(json == null) {
			logger.warn("bad request : no json data");
    		return badRequest("no json data");
    	} 
		
		offset = json.findPath("offset").asLong();
		
		if (offset == -1) {
			logger.warn("bad request: one or more parameters missing");
			return badRequest("one or more parameters missing");
		}
		
		try {
			words = wordManager.getAllWordsForASense(offset);
			JsonNode result = Json.toJson(words);
			
			return ok(result);		
			
		} catch (AruthAPIException e) {
			
			if (e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND || 
					e.getErrorCode() == ErrorCodes.SENSE_NOT_FOUND) {
				
				return badRequest(e.getErrorCode());
						
			} else {
				
				return internalServerError(e.getErrorCode());
				
			}
		}
	}	
}
