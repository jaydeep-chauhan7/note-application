package com.example.notes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Email(message = "Invalid Email Id")
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be 8 characters")
    @Column(nullable = false)
    @ToString.Exclude
    private String password;

    @CreationTimestamp
    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "last_update_time")
    private LocalDateTime lastUpdateTime;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @JsonIgnore
    private List<Note> notes = new ArrayList<>();
}

