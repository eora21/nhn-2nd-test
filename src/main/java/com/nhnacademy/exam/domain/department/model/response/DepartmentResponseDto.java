package com.nhnacademy.exam.domain.department.model.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.nhnacademy.exam.domain.department.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonRootName("department")
public class DepartmentResponseDto {
    private String id;
    private String name;

    public static DepartmentResponseDto toDto(Department department) {
        return new DepartmentResponseDto(department.getId(), department.getName());
    }
}
