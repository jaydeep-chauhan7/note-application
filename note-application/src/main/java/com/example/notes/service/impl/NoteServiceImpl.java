package com.example.notes.service.impl;


import com.example.notes.dto.NoteDto;
import com.example.notes.entity.Note;
import com.example.notes.entity.User;
import com.example.notes.exception.NoteNotFoundException;
import com.example.notes.exception.UserNotFoundException;
import com.example.notes.repository.NoteRepository;
import com.example.notes.repository.UserRepository;
import com.example.notes.service.NoteService;
import com.example.notes.utility.ResultJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    Logger logger = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    NoteRepository noteRepository;

    @Override
    public ResultJson createNote(NoteDto noteDto, String email) {
        logger.info("createNote: User email: {}", email);
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isEmpty()) {
            logger.error("createNote: User not found: {}", email);
            return new ResultJson(0, email, "User not found", HttpStatus.NOT_FOUND);
        }
        User user = existingUser.get();
        Note note = new Note();
        note.setUser(user);
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        note.setCreateTime(LocalDateTime.now());
        note.setLastUpdateTime(LocalDateTime.now());
        noteRepository.save(note);
        return new ResultJson(1, "SUCCESS", "Note created successfully", HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public List<NoteDto> getAllNotes(String email) {
        logger.info("getAllNotes: User with email {}", email);
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isEmpty()) {
            logger.error("getAllNotes: User not found: {}", email);
            return Collections.emptyList();
        }
        List<Note> noteList = existingUser.get().getNotes();
        if (noteList.isEmpty()) {
            logger.error("getAllNotes :: no notes found for email: {}", email);
            return Collections.emptyList();
        }
        return noteList.stream().map(note -> {
            NoteDto dto = new NoteDto();
            dto.setNote_id(note.getNote_id());
            dto.setTitle(note.getTitle());
            dto.setContent(note.getContent());
            dto.setCreateTime(note.getCreateTime());
            dto.setLastUpdateTime(note.getLastUpdateTime());
            return dto;
        }).toList();
    }

    @Override
    public NoteDto getNote(Long note_id, String email) throws UserNotFoundException, NoteNotFoundException {
        logger.info("getNote: Note id is {} and User email {}", note_id, email);
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isEmpty()) {
            logger.error("getNote: User not found: {}", email);
            throw new UserNotFoundException("User not found with email: " + email);
        }
        Optional<Note> existingNote = noteRepository.findByIdAndUserId(note_id, email);
        if (existingNote.isEmpty()) {
            logger.error("getNote: Note not found: {}", note_id);
            throw new NoteNotFoundException("Note not found with noteId: " + note_id + " and email: " + email);
        }
        Note note = existingNote.get();
        NoteDto noteDto = new NoteDto();
        noteDto.setNote_id(note.getNote_id());
        noteDto.setTitle(note.getTitle());
        noteDto.setContent(note.getContent());
        noteDto.setCreateTime(note.getCreateTime());
        noteDto.setLastUpdateTime(note.getLastUpdateTime());
        return noteDto;
    }

    @Override
    public ResultJson updateNote(Long note_id, NoteDto updatedNoteDto, String email) {
        logger.info("updateNote: Note id is {} and User email {}", note_id, email);
        Optional<Note> existingNote = noteRepository.findByIdAndUserId(note_id, email);
        if (existingNote.isEmpty()) {
            logger.error("updateNote: Note not found: {}", note_id);
            return new ResultJson(0, "FAILED", "Note not found", HttpStatus.NOT_FOUND);
        }
        Note note = existingNote.get();
        note.setTitle(updatedNoteDto.getTitle());
        note.setContent(updatedNoteDto.getContent());
        noteRepository.save(note);
        return new ResultJson(1, "SUCCESS", "Note updated successfully", HttpStatus.ACCEPTED);
    }

    @Override
    public ResultJson deleteNote(Long note_id, String email) {
        logger.info("deleteNote: Note id is {} and User email {}", note_id, email);
        Optional<Note> existingNote = noteRepository.findByIdAndUserId(note_id, email);
        if (existingNote.isEmpty()) {
            logger.error("deleteNote: Note not found: {}", note_id);
            return new ResultJson(0, "FAILED", "Note not found", HttpStatus.NOT_FOUND);
        }
        Note note = existingNote.get();
        noteRepository.delete(note);
        return new ResultJson(1, "SUCCESS", "Note deleted successfully", HttpStatus.ACCEPTED);
    }
}
