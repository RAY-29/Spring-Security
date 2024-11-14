package com.vraj.springSecurity.service;

import com.vraj.springSecurity.entity.JournalEntry;
import com.vraj.springSecurity.entity.User;
import com.vraj.springSecurity.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void saveEntry(User user) {
      userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getJournalEntryById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteEntryById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }
}
