/*
 * Requests for senses of a word are handled by this class
 */
package controllers;

import java.util.ArrayList;
import java.util.List;

import net.sf.extjwnl.data.Synset;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import exceptions.AruthAPIException;
import exceptions.ErrorCodes;

import managers.SenseManager;
import models.SenseList;
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
		List<Synset> senses;
		List<String> glosses = new ArrayList<String>();
		List<Long> offsets = new ArrayList<Long>();
		
		try {
			senses = senseManager.getAllSenses(target);
			
			for (Synset s : senses) {
				glosses.add(s.getGloss());
				offsets.add(s.getOffset());
			}
			
			JsonNode result = Json.toJson(new SenseList(glosses, offsets));
			
			return ok(result);
			
		} catch (AruthAPIException e) {
			
			if (e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND) {
				
				return badRequest(e.getErrorCode());
				
			} else {
				
				return internalServerError(e.getErrorCode());
			}
		}		
	}
	
	/*
	 * /getAllSesnes
	 * This below method is used to respond to a request with a Json data.
	 * Json request will contain the target word
	 * Then this will return the response as a list (Also a Json)
	 * ex : ["ශරීර කොටසක්|"මුවින් නොබැන සිටීම","කට ආහාර අනුභවය්ට යොදාගනී"", "ශරීර කොටසක්|"
	   * උදෑසනම නැගිට මුව සෝදාගන්න","මුහුණ සෝදනවා", "සිනාසෙද්දී ඇගේ මුව මඩළ රෝස පැහැයෙන් බබළයි"", "
	   * වනයේ වෙසෙන සත්වයෙකි|කැළෑවේ වෙසෙන මුව රංචුව,මුව මස් ජාවාරම අද ප්‍රභල ප්‍රශ්නයකි"]
	 * 
	 */
	public static Result getAllSensesUsingJason () throws JsonGenerationException, JsonMappingException {
		
		JsonNode json = request().body().asJson();
		
		if(json == null) {
			logger.warn("bad request : no json data");
    		return badRequest("no json data");
    	} 
		String target="";
		target = json.findPath("target").asText(); 
		
		if (target == null) {
			logger.warn("bad request: one or more parameters missing");
			return badRequest("one or more parameters missing");
		}
		logger.info("Senses requested for " + target);
				
		SenseManager senseManager = new SenseManager();
		List<Synset> senses;
		List<String> glosses = new ArrayList<String>();
		List<Long> offsets = new ArrayList<Long>();
		
		try {
			senses = senseManager.getAllSenses(target);
			
			for (Synset s : senses) {
				glosses.add(s.getGloss());
				offsets.add(s.getOffset());
			}
			
			JsonNode result = Json.toJson(new SenseList(glosses, offsets));
			
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
