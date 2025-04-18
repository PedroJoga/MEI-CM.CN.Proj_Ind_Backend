package com.example.backend.repositories;

import com.example.backend.domain.response.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
