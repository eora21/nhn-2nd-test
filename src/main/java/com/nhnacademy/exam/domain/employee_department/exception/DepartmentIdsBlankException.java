package com.nhnacademy.exam.domain.employee_department.exception;

import com.nhnacademy.exam.advice.exception.CustomException;
import org.springframework.http.HttpStatus;

public class DepartmentIdsBlankException extends CustomException {

    public DepartmentIdsBlankException() {
        super("Required request parameter 'departmentIds' for method parameter type String is not present",
                HttpStatus.BAD_REQUEST);
    }
}
