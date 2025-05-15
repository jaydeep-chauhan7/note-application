package com.example.notes.controller;

import com.example.notes.dto.NoteDto;
import com.example.notes.exception.NoteNotFoundException;
import com.example.notes.exception.UserNotFoundException;
import com.example.notes.service.NoteService;
import com.example.notes.utility.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    NoteService noteService;

    @PostMapping
    public ResponseEntity<ResultJson> createNote(@RequestBody NoteDto noteDto) {
        return ResponseEntity.ok(noteService.createNote(noteDto, getUserName()));
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes(getUserName()));
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<NoteDto> getNote(@PathVariable Long noteId) {
        try {
            return ResponseEntity.ok(noteService.getNote(noteId, getUserName()));
        } catch (UserNotFoundException | NoteNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<ResultJson> updateNote(@PathVariable Long noteId, @RequestBody NoteDto noteDto) {
        return ResponseEntity.ok(noteService.updateNote(noteId, noteDto, getUserName()));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<ResultJson> deleteNote(@PathVariable Long noteId) {
        return ResponseEntity.ok(noteService.deleteNote(noteId, getUserName()));
    }

    private static String getUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        return userDetails.getUsername();
    }
}
