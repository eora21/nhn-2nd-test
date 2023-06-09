package com.nhnacademy.exam.domain.department.exception;

import com.nhnacademy.exam.advice.exception.CustomException;
import org.springframework.http.HttpStatus;

public class DepartmentNotFoundException extends CustomException {

    public DepartmentNotFoundException(String departmentId) {
        super("department not found : " + departmentId, HttpStatus.NOT_FOUND);
    }
}
