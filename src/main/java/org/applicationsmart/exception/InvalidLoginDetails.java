package org.applicationsmart.exception;

public class InvalidLoginDetails extends EmailAppException{
    public InvalidLoginDetails(String message) {
        super(message);
    }
}
