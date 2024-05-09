package com.cmps211.collab.editor.Controller;

import com.cmps211.collab.editor.Service.*;
import com.cmps211.collab.editor.Model.*;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
}
