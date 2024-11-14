package com.vraj.springSecurity.controller;

import com.vraj.springSecurity.entity.JournalEntry;
import com.vraj.springSecurity.entity.User;
import com.vraj.springSecurity.service.JournalEntryService;
import com.vraj.springSecurity.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User user = userService.findByUsername(userName);
        List<JournalEntry> allJournals =  user.getJournalEntries();
        if(allJournals!=null && !allJournals.isEmpty()) {
            return new ResponseEntity<>(allJournals, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *
     * @param journalEntry
     * @param userName
     * @return
     */
    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry, @PathVariable String userName) {
        try {
            journalEntryService.saveEntry(journalEntry, userName);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{journalId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId journalId){
        Optional<JournalEntry> journalEntry = journalEntryService.getJournalEntryById(journalId);
        return journalEntry.map(
                entry -> new ResponseEntity<>(entry, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("id/{journalId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId journalId){
        journalEntryService.deleteEntryById(journalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{journalId}")
    public JournalEntry updateEntryById(@PathVariable ObjectId journalId, @RequestBody JournalEntry journalEntry){
        return null;
    }
}
