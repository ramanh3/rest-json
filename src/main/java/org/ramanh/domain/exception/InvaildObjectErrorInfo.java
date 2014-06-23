package org.ramanh.domain.exception;

import java.util.HashMap;
import java.util.Map;

public class InvaildObjectErrorInfo extends ErrorInfo {
	
	private static final long serialVersionUID = -6529759576625305738L;
	private Map<String, String> fieldErrors = new HashMap<>();

	
	public InvaildObjectErrorInfo() {
	}

	public InvaildObjectErrorInfo(InvalideObjectResponseException ex) {
		super(ex);
		this.setFieldErrors(ex.getFieldErrorsMap());
	}

	public Map<String, String> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(Map<String, String> fieldErrorsMap) {
		this.fieldErrors = fieldErrorsMap;
	}

}
