/*
 * All requests for WSD are handled by this class
 */
package controllers;

import managers.WSDManager;

import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.*;

public class WSDController extends Controller {

	/*
	 * /disambiguate
	 * This method will accept a json object which has two attributes 'context' and 'target'
	 * The attribute values will be passed to the WSDManager and the disambiguated sense will
	 * be returned
	 */
	public static Result disambiguate() {
		String context;
		String target;
		String sense;
		WSDManager wsdManager = new WSDManager();
		
		JsonNode json = request().body().asJson();
		
		if(json == null) {
    		return badRequest("no json data");
    	} 
		
		context = json.findPath("context").asText();
		target = json.findPath("target").asText();
		
		if (context == null || target == null) {
			return badRequest("one or more parameters missing");
		}
		
		
		
		return ok("method not yet implemented");
	}
}
