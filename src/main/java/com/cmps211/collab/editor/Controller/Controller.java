package com.cmps211.collab.editor.Controller;

import com.cmps211.collab.editor.Service.*;
import com.cmps211.collab.editor.Model.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final UserService userService;

    Controller(UserService us) {
        userService = us;
    }

    // @PostMapping("/signup")
    // public String signUp(@RequestBody String userInfo) {
    //     String[] userInformation = userInfo.split(":");
    //     return userService.signUp(userInformation[0], userInformation[1]);
    // }

    @PostMapping("/login")
    public ResponseEntity<User> logIn(@RequestBody User userInfo) {
        if (userService.logIn(userInfo)) {
            return ResponseEntity.ok().body(userInfo);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
