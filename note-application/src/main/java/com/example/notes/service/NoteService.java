package com.example.notes.service;

import com.example.notes.dto.NoteDto;
import com.example.notes.exception.UserNotFoundException;
import com.example.notes.utility.ResultJson;

import java.util.List;

public interface NoteService {
    ResultJson createNote(NoteDto noteDto, String email) throws UserNotFoundException;

    List<NoteDto> getAllNotes(String email);

    NoteDto getNote(Long note_id, String email);

    ResultJson updateNote(Long note_id, String email);

    ResultJson deleteNote(Long note_id, String email);

}
