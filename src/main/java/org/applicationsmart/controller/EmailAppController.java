package org.applicationsmart.controller;

import org.applicationsmart.dtos.request.LoginRequest;
import org.applicationsmart.dtos.request.RegisterRequest;
import org.applicationsmart.dtos.request.SendEmailRequest;
import org.applicationsmart.dtos.response.ApiResponse;
import org.applicationsmart.dtos.response.LoginResponse;
import org.applicationsmart.dtos.response.RegisterResponse;
import org.applicationsmart.dtos.response.SendEmailResponse;
import org.applicationsmart.exception.DomainNameException;
import org.applicationsmart.exception.InvalidDetailsFormat;
import org.applicationsmart.exception.InvalidEmailException;
import org.applicationsmart.exception.InvalidLoginDetails;
import org.applicationsmart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class EmailAppController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        RegisterResponse registerResponse = new RegisterResponse();
        try{
            userService.register(registerRequest);
            registerResponse.setMessage("Account created successfully");
            return new ResponseEntity<>(new ApiResponse(true, registerResponse), HttpStatus.CREATED);
        }
        catch (InvalidDetailsFormat ex){
            registerResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, registerResponse), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = new LoginResponse();
        try{
            userService.login(loginRequest);
            loginResponse.setMessage("login successfully");
            return new ResponseEntity<>(new ApiResponse(true, loginResponse), HttpStatus.CREATED);
        }
        catch (InvalidLoginDetails ex){
            loginResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, loginResponse), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("sendEmail")
    public ResponseEntity<?> sendEmail(@RequestBody SendEmailRequest sendEmailRequest){
        SendEmailResponse sendEmailResponse = new SendEmailResponse();
        try{
            userService.sendEmail(sendEmailRequest);
            sendEmailResponse.setMessage("Email sent");
            return new ResponseEntity<>(new ApiResponse(true, sendEmailResponse), HttpStatus.CREATED);
        }
        catch (InvalidEmailException ex){
            sendEmailResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, sendEmailResponse), HttpStatus.BAD_REQUEST);

        }
    }
    
}
