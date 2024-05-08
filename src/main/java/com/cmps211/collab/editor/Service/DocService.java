package com.cmps211.collab.editor.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (docRepository.findByDocNameAndAuthorName(doc.getDocName(), doc.getAuthorName()).isPresent()) {
            return false;
        }
        docRepository.save(doc);
        return true;
    }

    public List<Doc> myDocs(String authorName) {
        return docRepository.findByAuthorName(authorName);
    }
}
