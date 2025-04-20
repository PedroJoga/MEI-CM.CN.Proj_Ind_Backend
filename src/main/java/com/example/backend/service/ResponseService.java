package com.example.backend.service;

import com.example.backend.domain.comment.Comment;
import com.example.backend.domain.response.Response;
import com.example.backend.domain.user.User;
import com.example.backend.dto.ResponseRequestDTO;
import com.example.backend.dto.ResponseResponseDTO;
import com.example.backend.repositories.CommentRepository;
import com.example.backend.repositories.ResponseRepository;
import com.example.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseResponseDTO addResponse(ResponseRequestDTO responseRequestDTO) {
        User user = userRepository.findById(responseRequestDTO.userId())
                .orElseThrow(() -> new RuntimeException("User not found."));

        Comment comment = commentRepository.findById(responseRequestDTO.commentId())
                .orElseThrow(() -> new RuntimeException("Comment not found."));

        Response response = new Response();
        response.setText(responseRequestDTO.text());
        response.setUser(user);
        response.setAnonymous(responseRequestDTO.isAnonymous());
        response.setComment(comment);

        Response savedResponse = responseRepository.save(response);
        return new ResponseResponseDTO(
                savedResponse.getId(),
                savedResponse.getText(),
                savedResponse.getUser().getUserPhotoLink(),
                savedResponse.getUser().getUsername(),
                savedResponse.getCreatedAt()
        );
    }

    public List<ResponseResponseDTO> getResponsesByComment(Long commentId) {
        List<Response> responses = responseRepository.findByComment_Id(commentId);
        return responses.stream()
                .map(response -> new ResponseResponseDTO(
                        response.getId(),
                        response.getText(),
                        (response.isAnonymous()) ? "null" : response.getUser().getUserPhotoLink(),
                        (response.isAnonymous()) ? "Anonymous" : response.getUser().getUsername(),
                        response.getCreatedAt()))
                .collect(Collectors.toList());
    }
}
