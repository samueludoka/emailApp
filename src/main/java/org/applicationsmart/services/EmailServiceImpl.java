package org.applicationsmart.services;

import org.applicationsmart.data.model.EmailApp;
import org.applicationsmart.dtos.request.SendEmailRequest;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailAppService{
    @Override
    public String createAccount(String domainName, String id) {
        return null;
    }

    @Override
    public EmailApp findUserDomainName(String domainName) {
        return null;
    }

    @Override
    public String login(String domainName) {
        return null;
    }

    @Override
    public void sendEmail(SendEmailRequest sendEmailRequest) {

    }
}
