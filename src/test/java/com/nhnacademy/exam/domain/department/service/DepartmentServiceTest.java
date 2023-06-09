package com.nhnacademy.exam.domain.department.service;

import com.nhnacademy.exam.domain.department.entity.Department;
import com.nhnacademy.exam.domain.department.exception.DepartmentIdDuplicateException;
import com.nhnacademy.exam.domain.department.exception.DepartmentNotFoundException;
import com.nhnacademy.exam.domain.department.model.request.DepartmentRequestDto;
import com.nhnacademy.exam.domain.department.model.request.DepartmentUpdateDto;
import com.nhnacademy.exam.domain.department.model.response.DepartmentIdDto;
import com.nhnacademy.exam.domain.department.model.response.DepartmentResponseDto;
import com.nhnacademy.exam.domain.department.repository.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.Constructor;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {DepartmentService.class})
class DepartmentServiceTest {
    @Autowired
    DepartmentService departmentService;

    @MockBean
    DepartmentRepository departmentRepository;

    @Test
    void create() throws Exception {
        when(departmentRepository.save(any(Department.class)))
                .thenReturn(new Department("T1", "테스트부서1"));

        Constructor<DepartmentRequestDto> constructor = DepartmentRequestDto.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        DepartmentRequestDto departmentRequestDto = constructor.newInstance();

        DepartmentIdDto departmentIdDto = departmentService.create(departmentRequestDto);
        assertThat(departmentIdDto.getId()).isEqualTo("T1");
    }

    @Test
    @DisplayName("중복값 생성 에러")
    void duplicate() throws Exception {
        when(departmentRepository.existsById(any()))
                .thenReturn(true);

        Constructor<DepartmentRequestDto> constructor = DepartmentRequestDto.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        DepartmentRequestDto departmentRequestDto = constructor.newInstance();

        assertThrows(DepartmentIdDuplicateException.class, () -> departmentService.create(departmentRequestDto));
    }

    @Test
    void read() {
        when(departmentRepository.findDtoById(any(String.class)))
                .thenReturn(Optional.of(new DepartmentResponseDto("T1", "테스트부서1")));

        DepartmentResponseDto departmentResponseDto = departmentService.read("T1");
        assertThat(departmentResponseDto.getId()).isEqualTo("T1");
        assertThat(departmentResponseDto.getName()).isEqualTo("테스트부서1");
    }

    @Test
    @DisplayName("값 존재하지 않음 에러")
    void readWrongData() {
        when(departmentRepository.findDtoById(any(String.class)))
                .thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> departmentService.read("T1"));
    }

    @Test
    void update() throws Exception {
        Department department = new Department("T1", "테스트부서1");
        when(departmentRepository.findById(any(String.class)))
                .thenReturn(Optional.of(department));

        Constructor<DepartmentUpdateDto> constructor = DepartmentUpdateDto.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        DepartmentUpdateDto departmentRequestDto = constructor.newInstance();

        departmentService.update("T1", departmentRequestDto);


        assertThat(department.getId()).isEqualTo("T1");
        assertThat(department.getName()).isNull();
    }

    @Test
    void delete() {
        doNothing().when(departmentRepository).deleteById(any());
        departmentService.delete("T1");
        verify(departmentRepository, times(1)).deleteById(any());
    }
}