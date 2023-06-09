package com.nhnacademy.exam.domain.employee_department.service;

import com.nhnacademy.exam.domain.employee_department.model.response.EmployeeDepartmentResponseDto;
import com.nhnacademy.exam.domain.employee_department.repository.EmployeeDepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeDepartmentService {
    private final EmployeeDepartmentRepository employeeDepartmentRepository;

    @Transactional(readOnly = true)
    public List<EmployeeDepartmentResponseDto> findAll(String[] departmentIds) {
        return employeeDepartmentRepository.findAllByPkDepartmentIdIn(List.of(departmentIds)).stream()
                .map(EmployeeDepartmentResponseDto::toDto)
                .collect(Collectors.toList());
    }
}
