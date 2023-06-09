package com.nhnacademy.exam.advice.controller_advice;

import com.nhnacademy.exam.advice.model.AdviceResponseDto;
import com.nhnacademy.exam.advice.exception.CustomException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;

@RestControllerAdvice
public class RestAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<AdviceResponseDto> exceptionHandler(CustomException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(new AdviceResponseDto(e.getMessage(), e.getStatus().value()));
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<AdviceResponseDto> exceptionHandler(ServletException e) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.badRequest()
                .headers(httpHeaders)
                .body(new AdviceResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
}
