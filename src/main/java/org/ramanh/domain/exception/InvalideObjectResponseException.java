package org.ramanh.domain.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class InvalideObjectResponseException extends ResponseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2969126916211707956L;
	private String errorCode = "100" + HttpStatus.UNPROCESSABLE_ENTITY;
	private Map<String,String> fieldErrorsMap = new HashMap<>();
	public InvalideObjectResponseException(BindingResult bindingResult) {
		super(String.format("Invalid object %s",bindingResult.getObjectName()));
		List<ObjectError> allErrors = bindingResult.getAllErrors();

		//Build the map of error 
		for (ObjectError objectError : allErrors) {
			if(objectError instanceof FieldError){
				//field level key fieldName, value message 
				FieldError fieldError  = (FieldError) objectError; 
				fieldErrorsMap.put(fieldError.getField(), fieldError.getDefaultMessage());
			}else{
				//TODO need to think about object level errors
			}
		}
	}

	
	public Map<String, String> getFieldErrorsMap() {
		return fieldErrorsMap;
	}


	@Override
	public String getErrorCode() {
		return errorCode;
	}

}
