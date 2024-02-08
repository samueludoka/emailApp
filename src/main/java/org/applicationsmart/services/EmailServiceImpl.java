package org.applicationsmart.services;

import org.applicationsmart.data.model.EmailApp;
import org.applicationsmart.data.model.Mail;
import org.applicationsmart.data.repository.EmailAppRepository;
import org.applicationsmart.dtos.request.SendEmailRequest;
import org.applicationsmart.exception.*;
import org.applicationsmart.utils.MailType;
import org.applicationsmart.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailAppService{
    @Autowired
    EmailAppRepository emailAppRepository;
    @Override
    public String createAccount(String domainName, String id) {
        if(domainNameExist(domainName))throw new DomainNameException("Domain Name Exist");
        EmailApp emailApp = new EmailApp();
        emailApp.setUserId(id);
        String response=domainName+"@small.com";
        emailApp.setDomainName(response);
        emailAppRepository.save(emailApp);
        return response;

    }

    public boolean domainNameExist(String domainName) {
        EmailApp emailApp = emailAppRepository.findByDomainName(domainName);
        return emailApp != null;
    }

    @Override
    public EmailApp findUserDomainName(String domainName) {
        EmailApp emailApp = emailAppRepository.findByDomainName(domainName);
        return emailApp;
    }

    @Override
    public String login(String domainName) {
        EmailApp emailApp =findUserDomainName(domainName);
        if(emailApp == null)throw new InvalidLoginDetails("Invalid login Details");
        emailApp.setLogIn(true);
        emailAppRepository.save(emailApp);
        return emailApp.getUserId();
    }

    @Override
    public void sendEmail(SendEmailRequest sendEmailRequest) {
        if(!domainNameExist(sendEmailRequest.getDomainName()))throw new UserExistException("User doesn't exist");
        EmailApp emailApp = findUserDomainName(sendEmailRequest.getDomainName());
        if(!emailApp.isLogIn())throw new InvalidLoginDetails("Kindly login");
        Mail mail = Mapper.mapMail(sendEmailRequest);
        List<Mail> allMail = emailApp.getMail();
        allMail.add(mail);
        emailApp.setMail(allMail);
        emailAppRepository.save(emailApp);
        EmailApp emailApp1 = findUserDomainName(sendEmailRequest.getReciepentEmail());
        if(emailApp1 == null)throw new InvalidEmailException("Invalid Email");
        mail.setMailType(MailType.INBOX);
        List<Mail> allMailForRecipient = emailApp.getMail();
        allMailForRecipient.add(mail);
        emailApp.setMail(allMailForRecipient);
        emailAppRepository.save(emailApp1);

    }
}
