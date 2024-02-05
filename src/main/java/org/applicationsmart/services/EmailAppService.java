package org.applicationsmart.services;

import org.applicationsmart.data.model.EmailApp;
import org.applicationsmart.dtos.request.SendEmailRequest;

public interface EmailAppService {
    String createAccount(String domainName, String id);

    EmailApp findUserDomainName(String domainName);

    String login(String domainName);

    void sendEmail(SendEmailRequest sendEmailRequest);
}
