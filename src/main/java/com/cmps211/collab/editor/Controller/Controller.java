package com.cmps211.collab.editor.Controller;

import com.cmps211.collab.editor.Service.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final UserService userService;

    Controller(UserService us) {
        userService = us;
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody String userInfo) {
        String[] userInformation = userInfo.split(":");
        return userService.signUp(userInformation[0], userInformation[1]);
    }
}
