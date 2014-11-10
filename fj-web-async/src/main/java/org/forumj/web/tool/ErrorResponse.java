/**
 * 
 */
package org.forumj.web.tool;

import java.util.*;

/**
 * JSON object for server error response
 * @author Andrew V. Pogrebnyak
 */
public class ErrorResponse {

	/**
	 * Exception stack trace
	 */
	private List<String> stackTrace = new ArrayList<String>();

	/**
	 * Exception message
	 */
	private String message = "";

	public ErrorResponse(Throwable exception){
		if (exception.getMessage() != null){
			message = exception.getMessage(); 
		}
		for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
			stackTrace.add(stackTraceElement.toString());
		}
	}

	public List<String> getStackTrace() {
		return stackTrace;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * For detect object in browser
	 */
	public boolean isException(){
		return true;
	}

}
