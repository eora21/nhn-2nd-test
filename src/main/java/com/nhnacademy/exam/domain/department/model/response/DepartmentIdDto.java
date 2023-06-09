package com.nhnacademy.exam.domain.department.model.response;

import com.nhnacademy.exam.domain.department.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DepartmentIdDto {
    private final String id;

    public static DepartmentIdDto toDto(Department department) {
        return new DepartmentIdDto(department.getId());
    }
}
