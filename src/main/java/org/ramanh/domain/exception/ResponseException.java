package org.ramanh.domain.exception;

public abstract class ResponseException extends RuntimeException {

	private static final long serialVersionUID = 4287615882770866112L;
	
	public ResponseException(String message, Throwable cause) {
		super(message, cause);		
	}
	
	public ResponseException(String message) {
		super(message);
	}

	public abstract String getErrorCode();
	
}
