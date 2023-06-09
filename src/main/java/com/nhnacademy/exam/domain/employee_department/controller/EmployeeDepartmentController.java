package com.nhnacademy.exam.domain.employee_department.controller;

import com.nhnacademy.exam.domain.employee_department.exception.DepartmentIdsBlankException;
import com.nhnacademy.exam.domain.employee_department.model.response.EmployeeDepartmentResponseDto;
import com.nhnacademy.exam.domain.employee_department.service.EmployeeDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department-members")
public class EmployeeDepartmentController {
    private final EmployeeDepartmentService employeeDepartmentService;

    @GetMapping
    public ResponseEntity<List<EmployeeDepartmentResponseDto>> departmentRead(@RequestParam String departmentIds) {
        if (departmentIds.trim().length() == 0) {
            throw new DepartmentIdsBlankException();
        }

        String[] ids = departmentIds.split(",");
        return ResponseEntity.ok(employeeDepartmentService.findAll(ids));
    }
}
