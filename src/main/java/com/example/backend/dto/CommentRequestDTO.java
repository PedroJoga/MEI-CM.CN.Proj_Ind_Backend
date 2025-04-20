package com.example.backend.dto;

public record CommentRequestDTO(String text, Long userId, boolean isAnonymous) {
}
