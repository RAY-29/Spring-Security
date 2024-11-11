package com.vraj.springSecurity.controller;

import com.vraj.springSecurity.entity.JournalEntry;
import com.vraj.springSecurity.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(journalEntry);
        return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
    }

    @GetMapping("id/{journalId}")
    public JournalEntry getEntryById(@PathVariable ObjectId journalId){
        return journalEntryService.getJournalEntryById(journalId).orElse(null);
    }

    @DeleteMapping("id/{journalId}")
    public boolean deleteEntryById(@PathVariable ObjectId journalId){
        journalEntryService.deleteEntryById(journalId);
        return true;
    }

    @PutMapping("id/{journalId}")
    public JournalEntry updateEntryById(@PathVariable ObjectId journalId, @RequestBody JournalEntry journalEntry){
        return null;
    }
}
