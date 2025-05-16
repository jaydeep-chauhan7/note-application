package com.example.notes.controller;

import com.example.notes.dto.NoteDto;
import com.example.notes.exception.NoteNotFoundException;
import com.example.notes.exception.UserNotFoundException;
import com.example.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/notes")
public class WebNoteController {

    @Autowired
    NoteService noteService;

    @GetMapping
    public String getAllNote(Model model) {
        List<NoteDto> notes = noteService.getAllNotes(getUserName());
        model.addAttribute("notes", notes);
        return "notes";
    }

    @GetMapping("/create")
    public String createNote(Model model) {
        model.addAttribute("create-note", new NoteDto());
        return "create-note";
    }

    @PostMapping("/create")
    public String createNote(@ModelAttribute NoteDto noteDto) {
        noteService.createNote(noteDto, getUserName());
        return "redirect:/web/notes";
    }

    @GetMapping("/update/{noteId}")
    public String editNoteForm(@PathVariable Long noteId, Model model) throws UserNotFoundException, NoteNotFoundException {
        NoteDto noteDto = noteService.getNote(noteId, getUserName());
        model.addAttribute("noteDto", noteDto);
        return "edit-note";
    }

    @PostMapping("/update/{noteId}")
    public String updateNote(@PathVariable Long noteId, @ModelAttribute NoteDto updatedNoteDto) {
        noteService.updateNote(noteId, updatedNoteDto, getUserName());
        return "redirect:/web/notes";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable Long noteId) {
        noteService.deleteNote(noteId, getUserName());
        return "redirect:/web/notes";
    }

    private static String getUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        return userDetails.getUsername();
    }
}
