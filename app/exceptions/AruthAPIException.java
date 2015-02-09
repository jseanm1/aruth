package exceptions;

/**
 * Generic Aruth API Exception
 * @author gjanindu
 *
 */
public class AruthAPIException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	public AruthAPIException (String errorCode, String message, Throwable t) {
		super(message, t);
		this.errorCode = errorCode;
		
	}
	
	public String getErrorCode () {
		System.out.print(errorCode);
		return errorCode;
	}

}
