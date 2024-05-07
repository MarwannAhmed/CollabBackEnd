package com.cmps211.collab.editor.Controller;

import com.cmps211.collab.editor.Service.*;
import com.cmps211.collab.editor.Model.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @PostMapping("/login")
    public ResponseEntity<User> logIn(@RequestBody User userInfo) {
        if (userService.logIn(userInfo)) {
            return ResponseEntity.ok().body(userInfo);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User userInfo) {
        if (userService.signUp(userInfo)) {
            return ResponseEntity.ok().body(userInfo);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @PostMapping("/adddoc")
    public ResponseEntity<Doc> create(@RequestBody Doc docInfo) {
        if (docService.create(docInfo)) {
            return ResponseEntity.ok().body(docInfo);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @GetMapping("/doc/{name}/{username}")
    public ResponseEntity<Doc> get(@PathVariable String name, @PathVariable String username) {
        Doc doc;
        if ((doc = docService.get(name, username)) != null) {
            return ResponseEntity.ok().body(doc);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
