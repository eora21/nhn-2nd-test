package com.nhnacademy.exam.domain.department.controller;

import com.nhnacademy.exam.domain.department.model.request.DepartmentRequestDto;
import com.nhnacademy.exam.domain.department.model.request.DepartmentUpdateDto;
import com.nhnacademy.exam.domain.department.model.response.DepartmentIdDto;
import com.nhnacademy.exam.domain.department.model.response.DepartmentResponseDto;
import com.nhnacademy.exam.domain.department.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Constructor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest(classes = {DepartmentController.class})
class DepartmentControllerTest {
    @Autowired
    DepartmentController departmentController;

    @MockBean
    DepartmentService departmentService;

    @Test
    void create() throws Exception {
        DepartmentIdDto departmentIdDto = new DepartmentIdDto("T1");
        when(departmentService.create(any()))
                .thenReturn(departmentIdDto);

        Constructor<DepartmentRequestDto> constructor = DepartmentRequestDto.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        DepartmentRequestDto departmentRequestDto = constructor.newInstance();

        ResponseEntity<DepartmentIdDto> responseEntity = departmentController.create(departmentRequestDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo(departmentIdDto);
    }

    @Test
    void read() {
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto("T1", "TEST");
        when(departmentService.read(any()))
                .thenReturn(departmentResponseDto);

        ResponseEntity<DepartmentResponseDto> responseEntity = departmentController.read("T1");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(departmentResponseDto);
    }

    @Test
    void update() throws Exception {
        doNothing().when(departmentService).update(any(), any());

        Constructor<DepartmentUpdateDto> constructor = DepartmentUpdateDto.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        DepartmentUpdateDto departmentRequestDto = constructor.newInstance();

        ResponseEntity<Void> responseEntity = departmentController.update("T1", departmentRequestDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(departmentService, times(1)).update(any(), any());
    }

    @Test
    void delete() {
        doNothing().when(departmentService).delete(any());

        ResponseEntity<Void> responseEntity = departmentController.delete("T1");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(departmentService, times(1)).delete(any());
    }
}