package org.applicationsmart.exception;

public class UserExistException extends EmailAppException{
    public UserExistException(String message){
        super(message);
    }

}
