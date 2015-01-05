package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import managers.SampleManager;
import play.mvc.Controller;
import play.mvc.Result;

public class SampleController extends Controller{

	/*
	 * /sample/get/
	 * DO NOT implement anything that goes in the deployment
	 */
	public static Result get() {
		
		return ok("responding to get");
	}
	
	public static Result post() {
		/*
		 * At the moment this method just accept the value of attribute 'name' in the json object
		 * passed and search the WordNet for a noun and return the IndexWord as a string
		 */
		JsonNode json = request().body().asJson();
		String word;
		String s;
		
		if(json == null) {
    		return badRequest("no json data");
    	} 
    	
    	word = json.findPath("name").textValue();
    	
    	 if(word == null) {
    		 return badRequest("parameter 'name' missing");
    	 }
    	 
		s = new SampleManager().getNoun(word);
		
		return ok(s);
	}
}
