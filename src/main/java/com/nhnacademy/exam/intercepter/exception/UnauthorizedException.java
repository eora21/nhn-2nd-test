package com.nhnacademy.exam.intercepter.exception;

import com.nhnacademy.exam.advice.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CustomException {

    public UnauthorizedException() {
        super("Unauthorized",
                HttpStatus.UNAUTHORIZED);
    }
}
