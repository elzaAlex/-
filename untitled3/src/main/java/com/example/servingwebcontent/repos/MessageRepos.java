package com.example.servingwebcontent.repos;

import com.example.servingwebcontent.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MessageRepos extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);
}