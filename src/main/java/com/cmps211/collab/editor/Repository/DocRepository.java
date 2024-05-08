package com.cmps211.collab.editor.Repository;

import com.cmps211.collab.editor.Model.*;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocRepository extends MongoRepository<Doc, String> {
    Optional<Doc> findByDocName(String docName);
}
