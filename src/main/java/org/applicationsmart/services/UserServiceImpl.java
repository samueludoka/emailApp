package org.applicationsmart.services;

import org.applicationsmart.data.model.User;
import org.applicationsmart.data.repository.UserRepository;
import org.applicationsmart.dtos.request.LoginRequest;
import org.applicationsmart.dtos.request.RegisterRequest;
import org.applicationsmart.dtos.request.SendEmailRequest;
import org.applicationsmart.exception.DomainNameException;
import org.applicationsmart.exception.InvalidLoginDetails;
import org.applicationsmart.exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.applicationsmart.utils.Verification;
import org.applicationsmart.exception.InvalidDetailsFormat;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailAppService emailAppService;

    @Override
    public String register(RegisterRequest registerRequest) {
        User user = new User();
        if (emailAppService.findUserDomainName(registerRequest.getDomainName()+"@small.com") != null) throw new  DomainNameException("user already exist");
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
        String userId = emailAppService.login(loginRequest.getDomainName());
        verifyPassword(userId, loginRequest.getPassword());
    }

    private void verifyPassword(String id, String password) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) throw new UserExistException("User doesn't exist");
        if(!user.get().getPassword().equals(password)) throw new InvalidLoginDetails("Invalid login details");
    }

    @Override
    public void sendEmail(SendEmailRequest sendEmailRequest) {
        emailAppService.sendEmail(sendEmailRequest);
    }
}
