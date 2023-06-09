package com.nhnacademy.exam.domain.employee_department.controller;

import com.nhnacademy.exam.domain.department.entity.Department;
import com.nhnacademy.exam.domain.department.model.request.DepartmentRequestDto;
import com.nhnacademy.exam.domain.department.model.response.DepartmentIdDto;
import com.nhnacademy.exam.domain.department.model.response.DepartmentResponseDto;
import com.nhnacademy.exam.domain.employee.entity.Employee;
import com.nhnacademy.exam.domain.employee.model.response.EmployeeResponseDto;
import com.nhnacademy.exam.domain.employee_department.entity.EmployeeDepartment;
import com.nhnacademy.exam.domain.employee_department.exception.DepartmentIdsBlankException;
import com.nhnacademy.exam.domain.employee_department.model.response.EmployeeDepartmentResponseDto;
import com.nhnacademy.exam.domain.employee_department.service.EmployeeDepartmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Constructor;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {EmployeeDepartmentController.class})
class EmployeeDepartmentControllerTest {

    @Autowired
    EmployeeDepartmentController employeeDepartmentController;

    @MockBean
    EmployeeDepartmentService employeeDepartmentService;

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    @DisplayName("파라미터 공백 에러")
    void departmentRead(String emptyLine) throws Exception {
        Assertions.assertThrows(DepartmentIdsBlankException.class,
                () -> employeeDepartmentController.departmentRead(emptyLine));
    }

    @Test
    void departmentRead() throws Exception {
        EmployeeResponseDto employee = new EmployeeResponseDto(21L, "ENAME");
        DepartmentResponseDto department = new DepartmentResponseDto("DID", "DNAME");
        List<EmployeeDepartmentResponseDto> employeeDepartments = List.of(
                new EmployeeDepartmentResponseDto(department, employee));

        when(employeeDepartmentService.findAll(any()))
                .thenReturn(employeeDepartments);

        List<EmployeeDepartmentResponseDto> responseDtoList = employeeDepartmentService.findAll(new String[]{"T1"});
        assertThat(responseDtoList).hasSize(1);

        DepartmentResponseDto departmentDto = responseDtoList.get(0).getDepartment();
        assertThat(departmentDto).isEqualTo(department);

        EmployeeResponseDto employeeDto = responseDtoList.get(0).getEmployee();
        assertThat(employeeDto).isEqualTo(employee);
    }

}