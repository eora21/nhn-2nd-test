package com.nhnacademy.exam.domain.employee.model.response;

import com.nhnacademy.exam.domain.employee.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeResponseDto {
    private Long id;
    private String name;

    public static EmployeeResponseDto toDto(Employee employee) {
        return new EmployeeResponseDto(employee.getId(), employee.getName());
    }
}
