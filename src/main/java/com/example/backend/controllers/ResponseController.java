package com.example.backend.controllers;

import com.example.backend.dto.ResponseRequestDTO;
import com.example.backend.dto.ResponseResponseDTO;
import com.example.backend.service.ResponseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responses")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @PostMapping("/")
    public ResponseEntity<ResponseResponseDTO> addResponse(@RequestBody @Valid ResponseRequestDTO responseRequestDTO) {
        ResponseResponseDTO responseResponseDTO = responseService.addResponse(responseRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseResponseDTO);
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<List<ResponseResponseDTO>> getResponsesByComment(@PathVariable Long commentId) {
        List<ResponseResponseDTO> responses = responseService.getResponsesByComment(commentId);
        return ResponseEntity.ok(responses);
    }

}
