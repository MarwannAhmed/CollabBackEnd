package com.cmps211.collab.editor.Controller;

import com.cmps211.collab.editor.Service.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final UserService userService;

    Controller(UserService us) {
        userService = us;
    }

    @GetMapping("/username")
    public String getUsername() {
        return userService.getUsername(0);
    }

    @GetMapping("/email")
    public String getEmail() {
        return userService.getEmail(0);
    }

    @GetMapping("/create/{username}/{email}/{password}")
    public void createUser(@PathVariable String username, @PathVariable String email, @PathVariable String password) {
        userService.createUser(username, email, password);
    }

    @GetMapping("/update/{username}/{password}")
    public void updateUser(@PathVariable String username, @PathVariable String password) {
        userService.updateUser(username, password);
    }

    @GetMapping("/delete/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }
}
