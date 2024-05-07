package com.cmps211.collab.editor.Controller;

import com.cmps211.collab.editor.Service.*;
import com.cmps211.collab.editor.Model.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final UserService userService;

    Controller(UserService us) {
        userService = us;
    }

    //@CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @PostMapping("/login")
    public ResponseEntity<User> logIn(@RequestBody User userInfo) {
        if (userService.logIn(userInfo)) {
            return ResponseEntity.ok().body(userInfo);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @CrossOrigin(origins = "https://collaborativeeditor.vercel.app")
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User userInfo) {
        if (userService.signUp(userInfo)) {
            return ResponseEntity.ok().body(userInfo);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
