package org.applicationsmart.data.repository;

import org.applicationsmart.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
