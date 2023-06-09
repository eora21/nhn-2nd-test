package com.nhnacademy.exam.domain.employee_department.model.response;


import com.nhnacademy.exam.domain.department.model.response.DepartmentResponseDto;
import com.nhnacademy.exam.domain.employee.model.response.EmployeeResponseDto;
import com.nhnacademy.exam.domain.employee_department.entity.EmployeeDepartment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeDepartmentResponseDto {
    private final DepartmentResponseDto department;
    private final EmployeeResponseDto employee;

    public static EmployeeDepartmentResponseDto toDto(EmployeeDepartment employeeDepartment) {
        return new EmployeeDepartmentResponseDto(
                DepartmentResponseDto.toDto(employeeDepartment.getPk().getDepartment()),
                EmployeeResponseDto.toDto(employeeDepartment.getPk().getEmployee()));
    }
}

