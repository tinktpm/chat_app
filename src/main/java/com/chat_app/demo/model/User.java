package com.chat_app.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Document("user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    private String _id;
    private String name;
    private String avatar;
    private String email;
    private String password;

    public User(String _id, String name, String avatar){
        this._id = _id;
        this.name = name;
        this.avatar = avatar;
    }
}
