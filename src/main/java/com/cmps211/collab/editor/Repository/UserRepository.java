package com.cmps211.collab.editor.Repository;

import com.cmps211.collab.editor.Model.*;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    
}
