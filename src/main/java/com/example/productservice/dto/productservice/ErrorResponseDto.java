package com.example.productservice.dto.productservice;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponseDto {
    private String message;
    private HttpStatus status;
}
