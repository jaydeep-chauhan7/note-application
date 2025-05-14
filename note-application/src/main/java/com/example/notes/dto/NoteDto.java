package com.example.notes.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoteDto {
    private Long note_id;
    private String title;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;
}
