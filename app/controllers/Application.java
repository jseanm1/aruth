package controllers;

import java.io.File;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
     
    	return ok(index.render("Aruth WSD is ready...."));
        //return ok(indexTest.render("Fine"));
    }
  
}
