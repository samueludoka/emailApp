package org.applicationsmart.exception;

public class InvalidEmailException extends EmailAppException{
    public InvalidEmailException(String message) {
        super(message);
    }
}
