package com.nhnacademy.exam.domain.employee.model.normal;

import com.nhnacademy.exam.domain.employee.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeDto {
    private final long id;
    private final String name;

    public static Employee toEntity(EmployeeDto employeeDto) {
        return new Employee(employeeDto.getId(), employeeDto.getName());
    }
}
