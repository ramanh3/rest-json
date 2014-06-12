package org.ramanh.domain.exception;

import org.springframework.http.HttpStatus;

public class NotFoundResponseException extends ResponseException {
	
	private static final long serialVersionUID = -2967355564384391101L;
	private String errorCode = "100"+HttpStatus.NOT_FOUND;
	
	public NotFoundResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundResponseException(String message) {
		super(message);
	}

	@Override
	public String getErrorCode() {
		return errorCode;
	}

}
