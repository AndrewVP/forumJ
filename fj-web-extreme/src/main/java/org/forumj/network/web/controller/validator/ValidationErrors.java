package org.forumj.network.web.controller.validator;

import java.util.*;

public class ValidationErrors {

	private List<ErrorCode> errors = new ArrayList<ErrorCode>();
	
	public void addError(ErrorCode error){
		errors.add(error);
	}

	/**
	 * @return the hasErrors
	 */
	public boolean isHasErrors() {
		return errors.size() > 0;
	}
	
	public List<ErrorCode> getErrors(){
		return errors;
	}

}
