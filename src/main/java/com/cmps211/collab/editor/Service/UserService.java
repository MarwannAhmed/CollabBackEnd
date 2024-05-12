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

    public boolean signUp(User user) {
        if (userRepository.findById(user.getUsername()).isPresent()) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public boolean logIn(User user) {
        return userRepository.findById(user.getUsername()).isPresent()
                && userRepository.findById(user.getUsername()).get().getPassword().equals(user.getPassword());
    }

    public boolean doesExist(String username) {
        if (userRepository.findById(username).isPresent()) {
            return true;
        }
        return false;
    }
}
