package org.ramanh.domain.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;

public class InvalideObjectResponseException extends ResponseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2969126916211707956L;
	 // HttpStatus Unprocessable Entity http://greenbytes.de/tech/webdav/rfc4918.html#rfc.section.11.2;
	private String errorCode = "100" + "422";
	private Map<String,String> feildErrorsMap = new HashMap<>();
	public InvalideObjectResponseException(BindingResult bindingResult) {
		super(String.format("Invalid object %s",bindingResult.getObjectName()));
	}

	
	@Override
	public String getErrorCode() {
		return errorCode;
	}

}
