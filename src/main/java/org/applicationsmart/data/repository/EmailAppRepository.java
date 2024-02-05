package org.applicationsmart.data.repository;

import org.applicationsmart.data.model.EmailApp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailAppRepository extends MongoRepository<EmailApp, String> {
    EmailApp findByDomainName(String domainName);
}
