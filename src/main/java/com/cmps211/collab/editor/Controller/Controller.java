package com.cmps211.collab.editor.Controller;

import com.cmps211.collab.editor.Service.*;
import com.cmps211.collab.editor.Model.*;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final UserService userService;
    private final DocService docService;

    Controller(UserService us, DocService ds) {
        userService = us;
        docService = ds;
    }

    // mapping for login requests
    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @PostMapping("/login")
    public ResponseEntity<User> logIn(@RequestBody User userInfo) {
        if (userService.logIn(userInfo)) {
            return ResponseEntity.ok().body(userInfo);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // mapping for signup requests
    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User userInfo) {
        if (userService.signUp(userInfo)) {
            return ResponseEntity.ok().body(userInfo);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    // mapping for document creation requests
    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @PostMapping("/adddoc")
    public ResponseEntity<Doc> create(@RequestBody Doc docInfo) {
        if (docService.create(docInfo)) {
            return ResponseEntity.ok().body(docInfo);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    // mapping for retrieval of owned documents requests
    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @GetMapping("/owneddocs/{author}")
    public ResponseEntity<List<Doc>> myDocs(@PathVariable String author) {
        return ResponseEntity.ok().body(docService.myDocs(author));
    }

    // mapping for retrieval of shared documents (with edit permission) requests
    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @GetMapping("/editdocs/{user}")
    public ResponseEntity<List<Doc>> editDocs(@PathVariable String user) {
        return ResponseEntity.ok().body(docService.editDocs(user));
    }

    // mapping for retrieval of shared documents (without edit permission) requests
    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @GetMapping("/viewdocs/{user}")
    public ResponseEntity<List<Doc>> viewDocs(@PathVariable String user) {
        return ResponseEntity.ok().body(docService.viewDocs(user));
    }

    // mapping for retrieval of document content requests
    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @GetMapping("/content/{docID}")
    public ResponseEntity<char[]> getContent(@PathVariable String docID) {
        return ResponseEntity.ok().body(docService.getContent(docID));
    }

    // mapping for retrieval of document viewers requests
    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @GetMapping("/viewers/{docID}")
    public ResponseEntity<List<String>> getViewers(@PathVariable String docID) {
        return ResponseEntity.ok().body(docService.getViewers(docID));
    }

    // mapping for retrieval of document editors requests
    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @GetMapping("/editors/{docID}")
    public ResponseEntity<List<String>> getEditors(@PathVariable String docID) {
        return ResponseEntity.ok().body(docService.getEditors(docID));
    }

    // mapping for renaming of documents requests
    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @PostMapping("/rename")
    public ResponseEntity<Doc> rename(@RequestBody Doc docInfo) {
        if (docService.rename(docInfo)) {
            return ResponseEntity.ok().body(docInfo);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    // mapping for deletion of documents requests
    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @DeleteMapping("/delete/{docID}")
    public ResponseEntity<Doc> delete(@PathVariable String docID) {
        docService.delete(docID);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // mapping for sharing of documents requests
    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @PostMapping("/share/{access}/{username}")
    public ResponseEntity<Doc> share(@RequestBody Doc docInfo, @PathVariable String access,
            @PathVariable String username) {
        Integer res;
        if (!userService.doesExist(username)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (access.equals("viewer")) {
            res = docService.makeViewer(docInfo.getDocID(), username);
            if (res == 0) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else if (res == 2) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            res = docService.makeEditor(docInfo.getDocID(), username);
            if (res == 0) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else if (res == 2) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
