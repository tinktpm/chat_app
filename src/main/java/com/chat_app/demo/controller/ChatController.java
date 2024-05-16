package com.chat_app.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chat_app.demo.model.Chat;
import com.chat_app.demo.service.ChatService;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {
    
    @Autowired 
    private ChatService chatService;

    @GetMapping("")
    public ResponseEntity<Object> getChatByUsers(@RequestParam String user1, @RequestParam String user2){
        List<String> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        Chat chat = chatService.getChatByUsers(users);

        if(chat != null){
            return new ResponseEntity<>(chat, HttpStatus.OK);
        }

        if(chat == null){
            users.removeAll(users);
            users.add(user2);
            users.add(user1);
            
            chat = chatService.getChatByUsers(users);
            
            if(chat != null){
                return new ResponseEntity<>(chat, HttpStatus.OK);
            }
        }

        chat = chatService.addChat(new Chat(users));
        return new ResponseEntity<>(chat, HttpStatus.CREATED);
    }
}
