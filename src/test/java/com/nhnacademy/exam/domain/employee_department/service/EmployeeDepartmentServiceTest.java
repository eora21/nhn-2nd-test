package com.nhnacademy.exam.domain.employee_department.service;

import com.nhnacademy.exam.domain.department.entity.Department;
import com.nhnacademy.exam.domain.department.model.response.DepartmentResponseDto;
import com.nhnacademy.exam.domain.employee.entity.Employee;
import com.nhnacademy.exam.domain.employee.model.response.EmployeeResponseDto;
import com.nhnacademy.exam.domain.employee_department.entity.EmployeeDepartment;
import com.nhnacademy.exam.domain.employee_department.model.response.EmployeeDepartmentResponseDto;
import com.nhnacademy.exam.domain.employee_department.repository.EmployeeDepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {EmployeeDepartmentService.class})
class EmployeeDepartmentServiceTest {
    @Autowired
    EmployeeDepartmentService employeeDepartmentService;

    @MockBean
    EmployeeDepartmentRepository employeeDepartmentRepository;

    @Test
    void findAll() throws Exception {
        Employee employee = new Employee(21L, "ENAME");
        Department department = new Department("DID", "DNAME");
        List<EmployeeDepartment> employeeDepartments = List.of(
                new EmployeeDepartment(new EmployeeDepartment.Pk(employee, department)));

        when(employeeDepartmentRepository.findAllByPkDepartmentIdIn(any()))
                .thenReturn(employeeDepartments);

        List<EmployeeDepartmentResponseDto> responseDtoList = employeeDepartmentService.findAll(new String[]{"T1"});
        assertThat(responseDtoList).hasSize(1);

        DepartmentResponseDto departmentDto = responseDtoList.get(0).getDepartment();
        DepartmentResponseDto departmentEntityToDto = DepartmentResponseDto.toDto(department);
        assertThat(departmentDto.getId()).isEqualTo(departmentEntityToDto.getId());
        assertThat(departmentDto.getName()).isEqualTo(departmentEntityToDto.getName());

        EmployeeResponseDto employeeDto = responseDtoList.get(0).getEmployee();
        EmployeeResponseDto employeeEntityToDto = EmployeeResponseDto.toDto(employee);
        assertThat(employeeDto.getId()).isEqualTo(employeeEntityToDto.getId());
        assertThat(employeeDto.getName()).isEqualTo(employeeEntityToDto.getName());
    }
}