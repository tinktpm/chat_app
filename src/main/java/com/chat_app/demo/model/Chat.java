package com.chat_app.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Document("chat")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Chat {

    @Id
    private String _id;
    private List<String> users = new ArrayList<>();

    @DocumentReference(collection = "message")
    private List<Message> messages = new ArrayList<>();

    public Chat(List<String> users){
        this.users = users;
    }
}
