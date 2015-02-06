package controllers;

import com.fasterxml.jackson.databind.JsonNode; 

import managers.SampleManager;
 
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import play.libs.Json;
import com.fasterxml.jackson.databind.node.ObjectNode;

import exceptions.AruthAPIException;
import exceptions.ErrorCodes;

public class SampleController extends Controller{

	/*
	 * /sample/get/
	 * DO NOT implement anything that goes in the deployment
	 */
	public static Result get() {
		
		return ok("responding to get");
	}
	
	/*
	 * /sample/post/
	 * At the moment this method just accept the value of attribute 'name' in the json object
	 * passed and search the WordNet for a noun and return the IndexWord as a string
	 */
	public static Result post() {

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
    	 
		try {
			s = new SampleManager().getNoun(word);
		} catch (AruthAPIException e) {
			
			if (e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND) {
				
				return badRequest(e.getErrorCode());
				
			} else {
				
				return internalServerError(e.getErrorCode());
			}
		}
		
		return ok(s);
	}
	
	public static Result tryWordSenseDisambiguation() {
		// render the view to get user inputs from the browser
        return   ok(userInputForDisambiguate.render());
    }
	
	public static Result disambiguate(String context,String word) {
		// might have errors. just ignore them. refresh and run. it is an eclipse bug
		 
		// business code goes here ....
		
		// fetch the answer here
		
		// return the answer 
		
		// dummy return (input passed as result)
        return   ok(senseResponse.render(context,word));
    }
	
	public static Result disambiguatePost() {
		// Json will be passed to this method.
		
		JsonNode json = request().body().asJson();
		 
		if(json == null) { 
    		return badRequest("no json data");
    	}  
		
		String context = json.findPath("context").textValue();;
		String word = json.findPath("target").textValue();;
		// business code goes here ....
		
		// fetch the answer here
		
		// return the answer 
		ObjectNode result = Json.newObject(); 
		result.put("sense","correct_sense_gose_here"); 
		 
		//return   ok(senseResponse.render(context,word));
		//return  ok("<h1>Hello World!</h1>").as("text/html");
		//return  ok(Json.toJson("Vidudaya"));  //https://www.playframework.com/documentation/2.3.x/JavaJsonActions
		return ok(result);
    }
	
	public static Result publications(){		
        return ok(views.html.publications.render());
    }
	public static Result home(){
        return ok(views.html.home.render());

    } 
	
	public static Result aboutUs()
	{
		 return ok(views.html.aboutUs.render());
	}
	
	public static Result contactUs()
	{
		 return ok(views.html.contactUs.render());
	}
}
