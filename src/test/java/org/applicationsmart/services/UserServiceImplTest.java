package org.applicationsmart.services;

import org.applicationsmart.data.repository.EmailAppRepository;
import org.applicationsmart.data.repository.UserRepository;
import org.applicationsmart.dtos.request.LoginRequest;
import org.applicationsmart.dtos.request.RegisterRequest;
import org.applicationsmart.dtos.request.SendEmailRequest;
import org.applicationsmart.exception.DomainNameException;
import org.applicationsmart.exception.InvalidDetailsFormat;
import org.applicationsmart.exception.InvalidEmailException;
import org.applicationsmart.exception.InvalidLoginDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailAppRepository emailAppRepository;

    @AfterEach
    public void doThisAfter(){
        emailAppRepository.deleteAll();
        userRepository.deleteAll();
    }
    @Test
    public void testThatWhenUserRegisterRepositoryCountIncreases() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("smart");
        registerRequest.setAge("18");
        registerRequest.setPhoneNumber("07034356733");
        registerRequest.setPassword("password");
        registerRequest.setDomainName("smartdon111");
        String answer = userService.register(registerRequest);
        String result = "smartdon111@small.com";
        assertEquals(answer,result);
        assertEquals(1,userRepository.count());
    }
    @Test
    public void testThatRegisteredCantRegisterTwice(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("smart");
        registerRequest.setAge("18");
        registerRequest.setPhoneNumber("07034356733");
        registerRequest.setPassword("password");
        registerRequest.setDomainName("smartdon111");
        userService.register(registerRequest);
        assertThrows(DomainNameException.class,() -> userService.register(registerRequest));
    }
    @Test
    public void testThatWhenUserRegisterWithInvalidNumberItThrowsException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("opeoluwa");
        registerRequest.setAge("18");
        registerRequest.setPhoneNumber("12344");
        registerRequest.setPassword("opeoluwa123");
        registerRequest.setDomainName("opeoluaagnes");
        assertThrows(InvalidDetailsFormat.class,()->userService.register(registerRequest));
    }
    @Test
    public void testThatWhenUserLoginWithAWrongPasswordItThrowsAnException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("opeoluwa");
        registerRequest.setAge("18");
        registerRequest.setPhoneNumber("07066221008");
        registerRequest.setPassword("opeoluwa123");
        registerRequest.setDomainName("delighted");
        userService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest("delighted@small.com","opeoluwa23");
        assertThrows(InvalidDetailsFormat.class,()->userService.login(loginRequest));
    }
    @Test
    public void testThatWhenRegisterUserSendEmailToUnRegisterUserThrowsAnException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("opeoluwa");
        registerRequest.setAge("18");
        registerRequest.setPhoneNumber("07066221008");
        registerRequest.setPassword("opeoluwa123");
        registerRequest.setDomainName("delighted");
        userService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest("delighted@small.com","opeoluwa123");
        userService.login(loginRequest);
        SendEmailRequest sendEmailRequest = new SendEmailRequest("delighted@small.com",
                "I will like to apply for the secretary job offer",
                "I will like to apply for the secretary job offer,please consider my request",
                "opeoluwa@gmail.com");
        assertThrows(InvalidEmailException.class,()->userService.sendEmail(sendEmailRequest));
    }

}