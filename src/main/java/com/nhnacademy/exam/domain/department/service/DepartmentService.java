package com.nhnacademy.exam.domain.department.service;

import com.nhnacademy.exam.domain.department.entity.Department;
import com.nhnacademy.exam.domain.department.exception.DepartmentNotFoundException;
import com.nhnacademy.exam.domain.department.model.request.DepartmentRequestDto;
import com.nhnacademy.exam.domain.department.model.request.DepartmentUpdateDto;
import com.nhnacademy.exam.domain.department.model.response.DepartmentIdDto;
import com.nhnacademy.exam.domain.department.model.response.DepartmentResponseDto;
import com.nhnacademy.exam.domain.department.repository.DepartmentRepository;
import com.nhnacademy.exam.domain.department.exception.DepartmentIdDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentIdDto create(DepartmentRequestDto departmentRequestDto) {
        if (departmentRepository.existsById(departmentRequestDto.getId())) {
            throw new DepartmentIdDuplicateException(departmentRequestDto.getId());
        }

        Department department = DepartmentRequestDto.toEntity(departmentRequestDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentIdDto.toDto(savedDepartment);
    }

    @Transactional(readOnly = true)
    public DepartmentResponseDto read(String departmentId) {
        return departmentRepository.findDtoById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));
    }

    public void update(String departmentId, DepartmentUpdateDto departmentUpdateDto) {
        departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId))
                .setName(departmentUpdateDto.getName());
    }

    public void delete(String departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
