package com.chat_app.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat_app.demo.model.Message;
import com.chat_app.demo.service.MessageService;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {
    
    @Autowired
    private MessageService messageService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMessageById(@PathVariable String id){
        Message message = messageService.getMessage(id);

        if(message != null){
            return new ResponseEntity<Object>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
