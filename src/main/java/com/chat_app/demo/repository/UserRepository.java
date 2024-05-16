package com.chat_app.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chat_app.demo.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
    List<User> findAll();

    Optional<User> findBy_id (String _id);

    Optional<User> findByEmailAndPassword(String email, String password);
}
