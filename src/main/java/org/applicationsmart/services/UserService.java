package org.applicationsmart.services;

import org.applicationsmart.dtos.request.LoginRequest;
import org.applicationsmart.dtos.request.RegisterRequest;
import org.applicationsmart.dtos.request.SendEmailRequest;

public interface UserService {
    String register(RegisterRequest registerRequest);

    void login(LoginRequest loginRequest);

    void sendEmail(SendEmailRequest sendEmailRequest);


}
