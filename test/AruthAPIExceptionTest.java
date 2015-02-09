
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import managers.SenseManager;
import net.sf.extjwnl.data.Synset;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.AruthAPIException;
import exceptions.ErrorCodes;


public class AruthAPIExceptionTest {
	
	private final static String ERRORCODE = "SENSE_NOT_FOUND";
	private final static String ERROR="Sense not found.";
	private final static Throwable t=null;
	
	
	private static AruthAPIException aruthAPIException;
	
	@BeforeClass
	public static void prepare () {
		aruthAPIException = new AruthAPIException(ERRORCODE,ERROR,t);
	}
	/*
	 * Tests if AruthAPIException.getErrorCode is working properly
	 */

	
}
