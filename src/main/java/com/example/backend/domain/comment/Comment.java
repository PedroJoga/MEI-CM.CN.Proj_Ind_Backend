package com.example.backend.domain.comment;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Table(name = "comments")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Long userId;
    @NotNull
    private boolean isAnonymous;
    @CreationTimestamp
    private Timestamp createdAt;
}
