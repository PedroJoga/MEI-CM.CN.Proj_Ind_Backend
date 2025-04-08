package com.example.backend.domain.response;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Table(name = "responses")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Long userId;
    @NotNull
    private boolean isAnonymous;
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Long comment;
    @CreationTimestamp
    private Timestamp createdAt;
}
