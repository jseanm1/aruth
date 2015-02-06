package exceptions;

public class ErrorCodes {
	
	/*
	 * To make sure that this class is not instantiated
	 */
	private ErrorCodes() {
		
	}
	
	/*
	 * Define custom error codes as public final static String
	 */
	public final static String EXCEPTION_DEMO = "000000";
	
	public final static String CANNOT_OPEN_FILE = "000001";
	
	public final static String CANNOT_READ_FILE = "000002";

}
