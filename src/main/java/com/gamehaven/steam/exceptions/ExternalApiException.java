package com.gamehaven.steam.exceptions;

// …ExternalApiException.java
public class ExternalApiException extends RuntimeException {
    public ExternalApiException(String message, Throwable cause) { super(message, cause); }
}
