package com.cmps211.collab.editor.Service;

import com.cmps211.collab.editor.Model.User;
import com.cmps211.collab.editor.Repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository ur) {
        userRepository = ur;
    }

    public String signUp(String username, String password) {
        if (userRepository.findById(username).isPresent()) {
            return "F";
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userRepository.save(newUser);
        return "T";
    }

    public String logIn(String username, String password) {
        if (userRepository.findById(username).isPresent() && userRepository.findById(username).get().getPassword().equals(password)) {
            return "T";
        }
        return "F";
    }
}
