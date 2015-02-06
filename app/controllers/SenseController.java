/*
 * All requests for WSD are handled by this class
 */
package controllers;

import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import exceptions.AruthAPIException;
import exceptions.ErrorCodes;

import managers.SenseManager;
import play.Logger;
import play.Logger.ALogger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class SenseController extends Controller{

	private static final ALogger logger = Logger.of(SenseController.class);
	
	/*
	 * /getAllSenses
	 * This method will accept the attribute 'target'
	 * The attribute value will be passed to the WSDManager and all the senses will
	 * be returned as a list
	 */	
	public static Result getAllSenses (String target) throws JsonGenerationException, JsonMappingException {
		logger.info("Senses requested for " + target);
		
		SenseManager senseManager = new SenseManager();
		List<String> senses;
		
		try {
			senses = senseManager.getAllSenses(target);
			JsonNode result = Json.toJson(senses);
			
			return ok(result);
			
		} catch (AruthAPIException e) {
			
			if (e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND) {
				
				return badRequest(e.getErrorCode());
				
			} else {
				
				return internalServerError(e.getErrorCode());
			}
		}		
	}
}
