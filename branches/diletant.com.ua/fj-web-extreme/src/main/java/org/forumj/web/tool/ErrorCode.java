/**
 * 
 */
package org.forumj.web.tool;

/**
 * @author Andrew
 *
 */
public enum ErrorCode {
	AVATAR_REQUEST_TO_BIG(1, "avatar", "MSG_REQUEST_TO_BIG"),
	AVATAR_FILE_NOT_IMAGE(2, "avatar", "MSG_FILE_NOT_IMAGE");

	private int errorCode;
	private String fieldName;
	private String errorMessageNls;
	
	private ErrorCode(int errorCode, String fieldName, String errorMessageNls) {
		this.errorCode = errorCode;
		this.fieldName = fieldName;
		this.errorMessageNls = errorMessageNls;
	}

	
	
	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}



	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}



	/**
	 * @return the errorMessageNls
	 */
	public String getErrorMessageNls() {
		return errorMessageNls;
	}

	public static ErrorCode fromErrorCode(int errorCode){
		for (ErrorCode error : values()) {
			if (error.getErrorCode() == errorCode) return error;
		}
		throw new IllegalArgumentException("Unsupported error code: " + errorCode);
	}
}
