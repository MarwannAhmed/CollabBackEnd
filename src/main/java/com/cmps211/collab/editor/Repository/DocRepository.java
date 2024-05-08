package com.cmps211.collab.editor.Repository;

import com.cmps211.collab.editor.Model.*;

import java.util.Optional;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface DocRepository extends MongoRepository<Doc, String> {

    Optional<Doc> findByDocNameAndAuthorName(String docName, String authorName);

    @Query(value = "{ 'authorName': ?0 }", fields = "{ 'docID': 1, 'docName': 1 }")
    List<Doc> findByAuthorName(String authorName);

    @Query(value = "{ 'users': ?0 }", fields = "{ 'docID': 1, 'docName': 1 }")
    List<Doc> findByUsersContaining(String user);

}
