package com.example.backend.domain.comment;

import com.example.backend.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
    @NotNull
    @ColumnDefault("false")
    private boolean isAnonymous;
    @CreationTimestamp
    private Timestamp createdAt;
}
