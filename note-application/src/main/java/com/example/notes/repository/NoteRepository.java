package com.example.notes.repository;

import com.example.notes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n WHERE n.note_id = :noteId AND n.user.email = :email")
    Optional<Note> findByIdAndUserId(@Param("noteId") Long noteId, @Param("email") String email);
}
