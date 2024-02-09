package org.applicationsmart.exception;

import org.applicationsmart.data.model.EmailApp;

public class UserExistException2 extends EmailAppException {
    public UserExistException2(String message){
        super(message);
    }
}
