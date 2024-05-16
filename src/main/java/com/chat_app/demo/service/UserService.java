package com.chat_app.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat_app.demo.model.User;
import com.chat_app.demo.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User getuserByEmailAndPassword(String email, String password){
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    public User getUser(String id){
        return userRepository.findBy_id(id).orElse(null);
    }

    public List<User> getUsers(){
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
