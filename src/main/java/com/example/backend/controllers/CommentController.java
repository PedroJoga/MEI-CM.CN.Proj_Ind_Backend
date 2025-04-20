package com.example.backend.controllers;

import com.example.backend.domain.user.User;
import com.example.backend.dto.CommentRequestDTO;
import com.example.backend.dto.CommentResponseDTO;
import com.example.backend.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/")
    public ResponseEntity<CommentResponseDTO> addComment(Authentication authentication, @RequestBody @Valid CommentRequestDTO body) {
        User user = null;
        boolean isAnonymous = body.isAnonymous();

        if (authentication == null || !authentication.isAuthenticated()) {
            // Guest
            isAnonymous = true;
        } else {
            user = (User) authentication.getPrincipal();
        }

        CommentResponseDTO commentResponseDTO = commentService.addComment(body.text(), user, isAnonymous);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByUser(@PathVariable Long userId) { // TODO usar token em vez de userId
        List<CommentResponseDTO> comments = commentService.getCommentsByUser(userId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/random")
    public ResponseEntity<CommentResponseDTO> getRandomComment() {
        CommentResponseDTO randomComment = commentService.getRandomComment();
        return ResponseEntity.ok(randomComment);
    }
}
