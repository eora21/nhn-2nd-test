package com.nhnacademy.exam.domain.department.model.request;

import com.nhnacademy.exam.domain.department.entity.Department;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepartmentRequestDto {
    private String id;
    private String name;

    public static Department toEntity(DepartmentRequestDto departmentRequestDto) {
        return new Department(departmentRequestDto.getId(), departmentRequestDto.getName());
    }
}
