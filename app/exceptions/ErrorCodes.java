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
	
	public final static String CANNOT_CREATE_DICTIONARY = "000005";
	
	public final static String CANNOT_READ_DICTIONARY = "000004";
	
	public final static String WORD_NOT_FOUND = "100001";
	
	public final static String SENSE_NOT_FOUND = "100002";
	
	public final static String SENSE_OFFSET_MISMATCH = "200001";

}
