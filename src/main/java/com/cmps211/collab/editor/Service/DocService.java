package com.cmps211.collab.editor.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmps211.collab.editor.Model.Doc;
import com.cmps211.collab.editor.Repository.DocRepository;

@Service
public class DocService {
    private final DocRepository docRepository;

    @Autowired
    public DocService(DocRepository dr) {
        docRepository = dr;
    }

    public boolean create(Doc doc) {
        try {
            docRepository.save(doc);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
