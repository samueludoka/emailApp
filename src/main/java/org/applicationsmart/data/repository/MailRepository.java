package org.applicationsmart.data.repository;

import org.applicationsmart.data.model.Mail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MailRepository extends MongoRepository<Mail, String> {
    Mail findMailByMailType(String MailType);

}
