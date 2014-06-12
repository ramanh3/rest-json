package org.ramanh.domain.exception;

public class ErrorInfo {
    public final String errorCode;
    public final String errorMessage;

    public ErrorInfo(ResponseException ex) {
        this.errorCode = ex.getErrorCode();
        this.errorMessage = ex.getMessage();
    }
}