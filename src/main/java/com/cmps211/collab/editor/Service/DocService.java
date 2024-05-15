package com.cmps211.collab.editor.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.cmps211.collab.editor.Model.Doc;
import com.cmps211.collab.editor.Model.Message;
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

    public Integer makeViewer(String docID, String username) {
        Doc doc = docRepository.findById(docID).get();
        if (doc.getAuthorName().equals(username)) {
            return 2;
        }
        if (doc.getViewers().contains(username)) {
            return 0;
        }
        if (doc.getEditors().contains(username)) {
            List<String> editors = doc.getEditors();
            editors.remove(username);
            doc.setEditors(editors);
        }
        List<String> viewers = doc.getViewers();
        viewers.add(username);
        doc.setViewers(viewers);
        docRepository.save(doc);
        return 1;
    }

    public Integer makeEditor(String docID, String username) {
        Doc doc = docRepository.findById(docID).get();
        if (doc.getAuthorName().equals(username)) {
            return 2;
        }
        if (doc.getEditors().contains(username)) {
            return 0;
        }
        if (doc.getViewers().contains(username)) {
            List<String> viewers = doc.getViewers();
            viewers.remove(username);
            doc.setViewers(viewers);
        }
        List<String> editors = doc.getEditors();
        editors.add(username);
        doc.setEditors(editors);
        docRepository.save(doc);
        return 1;
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

    public synchronized void changeContent(Message message, String docID) {
        Doc doc = docRepository.findById(docID).get();
        char[] oldContent = doc.getContent();
        if (message.getOperation() == 0) {
            char[] newContent = new char[oldContent.length + 1];
            System.arraycopy(oldContent, 0, newContent, 0, message.getIndex());
            System.arraycopy(oldContent, message.getIndex(), newContent, message.getIndex() + 1,
                    oldContent.length - message.getIndex());
            newContent[message.getIndex()] = message.getCharacter();
            doc.setContent(newContent);
            docRepository.save(doc);
        } else {
            char[] newContent = new char[oldContent.length - 1];
            System.arraycopy(oldContent, 0, newContent, 0, message.getIndex());
            System.arraycopy(oldContent, message.getIndex() + 1, newContent, message.getIndex(),
                    oldContent.length - message.getIndex() - 1);
            doc.setContent(newContent);
            docRepository.save(doc);
        }
    }
}
