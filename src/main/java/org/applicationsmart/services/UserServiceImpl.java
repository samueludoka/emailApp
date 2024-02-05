package org.applicationsmart.services;

import org.applicationsmart.data.model.User;
import org.applicationsmart.data.repository.UserRepository;
import org.applicationsmart.dtos.request.LoginRequest;
import org.applicationsmart.dtos.request.RegisterRequest;
import org.applicationsmart.dtos.request.SendEmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.applicationsmart.utils.Verification;
import org.applicationsmart.exception.InvalidDetailsFormat;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailAppService emailAppService;

    @Override
    public String register(RegisterRequest registerRequest) {
        User user = new User();
        if(!Verification.verifyPhoneNumber(registerRequest.getPhoneNumber()))throw new InvalidDetailsFormat("Invalid phone Number");
        user.setName(registerRequest.getName());
        user.setAge(registerRequest.getAge());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setPassword(registerRequest.getPassword());
        userRepository.save(user);
        return emailAppService.createAccount(registerRequest.getDomainName(),user.getId());
    }

    @Override
    public void login(LoginRequest loginRequest) {

    }

    @Override
    public void sendEmail(SendEmailRequest sendEmailRequest) {

    }
}
