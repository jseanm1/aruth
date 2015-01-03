package controllers;

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
		
		return ok("responding to post");
	}
}
