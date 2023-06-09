package com.nhnacademy.exam.domain.department.exception;

import com.nhnacademy.exam.advice.exception.CustomException;
import org.springframework.http.HttpStatus;

public class DepartmentIdDuplicateException extends CustomException {

    public DepartmentIdDuplicateException(String departmentId) {
        super("부서 아이디 중복 : " + departmentId, HttpStatus.BAD_REQUEST);
    }
}
