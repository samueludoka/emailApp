package org.applicationsmart.utils;

import org.applicationsmart.data.model.Mail;
import org.applicationsmart.data.model.User;
import org.applicationsmart.dtos.request.SendEmailRequest;

public class Mapper {
    public  static Mail mapMail(SendEmailRequest sendEmailRequest) {
        Mail mail = new Mail();
        mail.setTitle(sendEmailRequest.getTitle());
        mail.setMailType(MailType.SENT);
        mail.setMessage(sendEmailRequest.getBody());
        return mail;
    }
}
