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

    public String getUsername(int index) {
        return userRepository.findAll().get(0).getUsername();
    }

    public String getEmail(int index) {
        return userRepository.findAll().get(0).getEmail();
    }

    public void createUser(String username, String email, String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        userRepository.save(newUser);
    }

    public void updateUser(String username, String password) {
        if (userRepository.findById(username).isPresent()) {
            User updatedUser = userRepository.findById(username).get();
            updatedUser.setPassword(password);
            userRepository.save(updatedUser);
        }
    }

    public void deleteUser(String username) {
        if (userRepository.findById(username).isPresent()) {
            userRepository.deleteById(username);
        }
    }
}
