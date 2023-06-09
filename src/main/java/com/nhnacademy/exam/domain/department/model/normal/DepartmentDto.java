package com.nhnacademy.exam.domain.department.model.normal;

import com.nhnacademy.exam.domain.department.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DepartmentDto {
    private final String id;
    private final String name;

    public static Department toEntity(DepartmentDto departmentDto) {
        return new Department(departmentDto.getId(), departmentDto.getName());
    }
}
