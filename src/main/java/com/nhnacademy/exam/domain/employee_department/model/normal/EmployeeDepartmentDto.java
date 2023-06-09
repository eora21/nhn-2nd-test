package com.nhnacademy.exam.domain.employee_department.model.normal;

import com.nhnacademy.exam.domain.department.model.normal.DepartmentDto;
import com.nhnacademy.exam.domain.employee.model.normal.EmployeeDto;
import com.nhnacademy.exam.domain.employee_department.entity.EmployeeDepartment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class EmployeeDepartmentDto {
    private final EmployeeDto employeeDto;
    private final DepartmentDto departmentDto;

    public static EmployeeDepartmentDto toDto(String[] departmentData) {
        if (departmentData.length != 4) {
            throw new IllegalStateException();
        }

        long identificationNumber = Long.parseLong(departmentData[0].trim());
        String name = departmentData[1].trim();
        String departmentName = departmentData[2].trim();
        String departmentCode = departmentData[3].trim();

        return new EmployeeDepartmentDto(
                new EmployeeDto(identificationNumber, name),
                new DepartmentDto(departmentCode, departmentName));
    }

    public static EmployeeDepartmentDto toDto(Map<String, String> departmentData) {
        if (departmentData.size() != 4) {
            throw new IllegalStateException();
        }

        long identificationNumber = Long.parseLong(departmentData.get("사번"));
        String name = departmentData.get("이름");
        String departmentName = departmentData.get("부서");
        String departmentCode = departmentData.get("부서코드");

        return new EmployeeDepartmentDto(
                new EmployeeDto(identificationNumber, name),
                new DepartmentDto(departmentCode, departmentName));
    }

    public static EmployeeDepartment toEntity(EmployeeDepartmentDto employeeDepartmentDto) {
        return new EmployeeDepartment(
                new EmployeeDepartment.Pk(
                        EmployeeDto.toEntity(employeeDepartmentDto.getEmployeeDto()),
                        DepartmentDto.toEntity(employeeDepartmentDto.getDepartmentDto())));
    }
}

