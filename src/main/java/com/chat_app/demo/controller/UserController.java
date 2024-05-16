package com.chat_app.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.chat_app.demo.model.User;
import com.chat_app.demo.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/user")

public class UserController{

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable String id){

        User user = userService.getUser(id);
        if(user != null){
            return new ResponseEntity<Object>(user, HttpStatus.OK);
        }

        return new ResponseEntity<Object>("", HttpStatus.NOT_FOUND);
    }

    @GetMapping("")
    public ResponseEntity<Object> getUsers(@RequestParam String id){

        List<User> users = userService.getUsers();
        if(users != null){
            users.removeIf(t -> t.get_id().equals(id));
            return new ResponseEntity<Object>(users, HttpStatus.OK);
        }

        return new ResponseEntity<Object>("", HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("")
    public ResponseEntity<Object> getUsers(@RequestBody Map<String, Object> map) {

        String id = map.get("id").toString();

        User user = userService.getUser(id);
        if(user != null){
            return new ResponseEntity<Object>(user, HttpStatus.OK);
        }

        return new ResponseEntity<Object>("", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, Object> map){
        String email = map.get("email").toString();
        String password = map.get("password").toString();

        try {
            User user = userService.getuserByEmailAndPassword(email, password);

            if(user != null){
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    
}