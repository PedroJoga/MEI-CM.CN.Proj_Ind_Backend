package com.example.backend.dto;

public record ResponseRequestDTO(String text, Long userId, boolean isAnonymous, Long commentId) {
}
