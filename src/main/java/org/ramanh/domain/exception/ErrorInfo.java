package org.ramanh.domain.exception;

import java.io.Serializable;

public class ErrorInfo implements Serializable {
	
	private static final long serialVersionUID = -6807178588030819379L;
	public String errorCode;
	public String errorMessage;

	public ErrorInfo() {
		super();
	}

	public ErrorInfo(ResponseException ex) {
		this.errorCode = ex.getErrorCode();
		this.errorMessage = ex.getMessage();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}