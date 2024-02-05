package org.applicationsmart.services;

import org.applicationsmart.data.repository.EmailAppRepository;
import org.applicationsmart.data.repository.UserRepository;
import org.applicationsmart.dtos.request.RegisterRequest;
import org.applicationsmart.exception.DomainNameException;
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
    public void testThatWhenUserRegisterRepositoryCountIncreases(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("smart");
        registerRequest.setAge("18");
        registerRequest.setPhoneNumber("07034356733");
        registerRequest.setPassword("password");
        registerRequest.setDomainName("smartdon111");
        String result = userService.register(registerRequest);
        String answer = "smartdon4@small.com";
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
}