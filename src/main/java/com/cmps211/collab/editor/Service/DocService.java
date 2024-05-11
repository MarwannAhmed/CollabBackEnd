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

    public List<Doc> editDocs(String user) {
        return docRepository.findByEditorsContaining(user);
    }

    public List<Doc> viewDocs(String user) {
        return docRepository.findByViewersContaining(user);
    }

    public char[] getContent(String docID) {
        return docRepository.findById(docID).get().getContent();
    }

    public List<String> getViewers(String docID) {
        return docRepository.findById(docID).get().getViewers();
    }

    public List<String> getEditors(String docID) {
        return docRepository.findById(docID).get().getEditors();
    }

    public void makeViewer(String docID, String editor) {
        Doc doc = docRepository.findById(docID).get();
        doc.getEditors().remove(editor);
        doc.getViewers().add(editor);
        docRepository.save(doc);
    }

    public void makeEditor(String docID, String viewer) {
        Doc doc = docRepository.findById(docID).get();
        doc.getViewers().remove(viewer);
        doc.getEditors().add(viewer);
        docRepository.save(doc);
    }

    public boolean rename(Doc doc) {
        if (docRepository.findByDocNameAndAuthorName(doc.getDocName(), doc.getAuthorName()).isPresent()) {
            return false;
        }
        Doc temp = docRepository.findById(doc.getDocID()).get();
        temp.setDocName(doc.getDocName());
        docRepository.save(temp);
        return true;
    }

    public void delete(String docID) {
        docRepository.deleteById(docID);
    }
}
