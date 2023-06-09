package com.nhnacademy.exam.event_listener;

import com.nhnacademy.exam.domain.department.entity.Department;
import com.nhnacademy.exam.domain.department.model.normal.DepartmentDto;
import com.nhnacademy.exam.domain.department.repository.DepartmentRepository;
import com.nhnacademy.exam.domain.employee.entity.Employee;
import com.nhnacademy.exam.domain.employee.model.normal.EmployeeDto;
import com.nhnacademy.exam.domain.employee.repository.EmployeeRepository;
import com.nhnacademy.exam.domain.employee_department.entity.EmployeeDepartment;
import com.nhnacademy.exam.domain.employee_department.model.normal.EmployeeDepartmentDto;
import com.nhnacademy.exam.domain.employee_department.repository.EmployeeDepartmentRepository;
import com.nhnacademy.exam.parser.DepartmentParserResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DepartmentDataUploadListener {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeDepartmentRepository employeeDepartmentRepository;
    private final DepartmentParserResolver departmentParserResolver;

    @EventListener(ApplicationReadyEvent.class)
    public void inputDepartmentData() throws IOException {
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("/data/*.*");
        List<EmployeeDepartmentDto> employeeDepartmentDtoList = new ArrayList<>();

        addAllDtoList(departmentParserResolver, resources, employeeDepartmentDtoList);
        Set<Department> departmentSet = getDepartmentSet(employeeDepartmentDtoList);
        Set<Employee> employeeSet = getEmployeeSet(employeeDepartmentDtoList);
        Set<EmployeeDepartment> employeeDepartmentSet = getEmployeeDepartmentSet(employeeDepartmentDtoList);

        saveAllEntity(departmentSet, employeeSet, employeeDepartmentSet);
    }

    private static void addAllDtoList(DepartmentParserResolver departmentParserResolver, Resource[] resources,
                                      List<EmployeeDepartmentDto> employeeDepartmentDtoList) throws IOException {
        for (Resource resource : resources) {
            employeeDepartmentDtoList.addAll(departmentParserResolver.getDepartmentParser(resource.getFilename())
                    .parsing(resource.getFile()));
        }
    }

    private static Set<Department> getDepartmentSet(List<EmployeeDepartmentDto> employeeDepartmentDtoList) {
        return employeeDepartmentDtoList.stream()
                .map(EmployeeDepartmentDto::getDepartmentDto)
                .map(DepartmentDto::toEntity)
                .collect(Collectors.toSet());
    }

    private static Set<Employee> getEmployeeSet(List<EmployeeDepartmentDto> employeeDepartmentDtoList) {
        return employeeDepartmentDtoList.stream()
                .map(EmployeeDepartmentDto::getEmployeeDto)
                .map(EmployeeDto::toEntity)
                .collect(Collectors.toSet());
    }

    private static Set<EmployeeDepartment> getEmployeeDepartmentSet(
            List<EmployeeDepartmentDto> employeeDepartmentDtoList) {
        return employeeDepartmentDtoList.stream()
                .map(EmployeeDepartmentDto::toEntity)
                .collect(Collectors.toSet());
    }

    private void saveAllEntity(Set<Department> departmentSet, Set<Employee> employeeSet,
                               Set<EmployeeDepartment> employeeDepartmentSet) {
        departmentRepository.saveAll(departmentSet);
        employeeRepository.saveAll(employeeSet);
        employeeDepartmentRepository.saveAll(employeeDepartmentSet);
    }
}
