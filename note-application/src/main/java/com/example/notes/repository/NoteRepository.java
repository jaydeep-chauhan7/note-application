package com.example.notes.repository;

import com.example.notes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("SELECT n FROM Note n JOIN n.user u WHERE u.id = :userId")
    List<Note> findByUserId(Long userId);
}
