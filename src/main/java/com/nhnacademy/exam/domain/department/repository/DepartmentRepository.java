package com.nhnacademy.exam.domain.department.repository;

import com.nhnacademy.exam.domain.department.entity.Department;
import com.nhnacademy.exam.domain.department.model.response.DepartmentResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    Optional<DepartmentResponseDto> findDtoById(String departmentId);
}
