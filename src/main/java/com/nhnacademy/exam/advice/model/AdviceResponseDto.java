package com.nhnacademy.exam.advice.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdviceResponseDto {
    private final String title;
    private final int status;
    private final LocalDateTime timestamp;

    public AdviceResponseDto(String title, int status) {
        this.title = title;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
