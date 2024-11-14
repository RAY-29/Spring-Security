package com.vraj.springSecurity.service;

import com.vraj.springSecurity.entity.JournalEntry;
import com.vraj.springSecurity.entity.User;
import com.vraj.springSecurity.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    /**
     *
     * @param journalEntry
     * @param userName
     */
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUsername(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public void deleteEntryById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }
}
