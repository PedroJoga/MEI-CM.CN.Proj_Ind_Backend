package com.example.backend.service;

import com.example.backend.domain.comment.Comment;
import com.example.backend.domain.user.User;
import com.example.backend.dto.CommentRequestDTO;
import com.example.backend.dto.CommentResponseDTO;
import com.example.backend.repositories.CommentRepository;
import com.example.backend.repositories.ResponseRepository;
import com.example.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private UserRepository userRepository;

    public CommentResponseDTO addComment(String text, User user, boolean isAnonymous) {

        Comment comment = new Comment();
        comment.setText(text);
        comment.setUser(user);
        comment.setAnonymous(isAnonymous);

        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDTO(
                savedComment.getId(),
                savedComment.getText(),
                savedComment.getUser().getUserPhotoLink(),
                savedComment.getUser().getUsername(),
                savedComment.getCreatedAt()
        );
    }

    public List<CommentResponseDTO> getCommentsByUser(Long userId) {
        List<Comment> comments = commentRepository.findByUser_Id(userId);

        return comments.stream()
                .map(comment -> new CommentResponseDTO(
                        comment.getId(),
                        comment.getText(),
                        comment.getUser().getUserPhotoLink(),
                        comment.getUser().getUsername(),
                        comment.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public CommentResponseDTO getRandomComment() {
        List<Comment> comments = commentRepository.findAll();

        if (comments.isEmpty()) {
            throw new RuntimeException("No comments available.");
        }

        int randomIndex = new Random().nextInt(comments.size());
        Comment randomComment = comments.get(randomIndex);

        return new CommentResponseDTO(
                randomComment.getId(),
                randomComment.getText(),
                (randomComment.isAnonymous()) ? "null" : randomComment.getUser().getUserPhotoLink(),
                (randomComment.isAnonymous()) ? "Anonymous" : randomComment.getUser().getUsername(),
                randomComment.getCreatedAt()
        );
    }


}
