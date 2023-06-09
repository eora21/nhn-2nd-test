package com.nhnacademy.exam.domain.department.controller;

import com.nhnacademy.exam.domain.department.model.request.DepartmentRequestDto;
import com.nhnacademy.exam.domain.department.model.request.DepartmentUpdateDto;
import com.nhnacademy.exam.domain.department.model.response.DepartmentIdDto;
import com.nhnacademy.exam.domain.department.model.response.DepartmentResponseDto;
import com.nhnacademy.exam.domain.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentIdDto> create(@RequestBody DepartmentRequestDto departmentRequestDto) {
        DepartmentIdDto departmentIdDto = departmentService.create(departmentRequestDto);
        return ResponseEntity.created(URI.create("/departments/" + departmentIdDto.getId()))
                .body(departmentIdDto);
    }

    @GetMapping("{departmentId}")
    public ResponseEntity<DepartmentResponseDto> read(@PathVariable String departmentId) {
        return ResponseEntity.ok(departmentService.read(departmentId));
    }

    @PutMapping("{departmentId}")
    public ResponseEntity<Void> update(@PathVariable String departmentId,
                                       @RequestBody DepartmentUpdateDto departmentUpdateDto) {
        departmentService.update(departmentId, departmentUpdateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{departmentId}")
    public ResponseEntity<Void> delete(@PathVariable String departmentId) {
        departmentService.delete(departmentId);
        return ResponseEntity.noContent().build();
    }
}
