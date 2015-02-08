/*
 * All requests for WSD are handled by this class
 */
package controllers;

import java.io.FileNotFoundException;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.Synset;
import managers.WSDManager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import exceptions.AruthAPIException;
import exceptions.ErrorCodes;

import play.Logger;
import play.Logger.ALogger;
import play.libs.Json;
import play.mvc.*;

public class WSDController extends Controller {

	private static final ALogger logger = Logger.of(WSDController.class);
	/*
	 * /disambiguate
	 * This method will accept a json object which has two attributes 'context' and 'target'
	 * The attribute values will be passed to the WSDManager and the disambiguated sense will
	 * be returned
	 */
	public static Result disambiguate() throws FileNotFoundException, JWNLException {
		String context;
		String target;
		Synset sense;
		WSDManager wsdManager = new WSDManager();
		
		JsonNode json = request().body().asJson();
		
		if(json == null) {
			logger.warn("bad request : no json data");
    		return badRequest("no json data");
    	} 
		
		context = json.findPath("context").asText();
		target = json.findPath("target").asText();
		
		if (context == null || target == null) {
			logger.warn("bad request: one or more parameters missing");
			return badRequest("one or more parameters missing");
		}
		
		logger.info("disambiguating target: " + target + " for context: " + context);
		
		try {
			sense = wsdManager.getSense(context, target);
			ObjectNode result = Json.newObject(); 
			result.put("sense",sense.getGloss());
			result.put("offset", sense.getOffset());
			
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
