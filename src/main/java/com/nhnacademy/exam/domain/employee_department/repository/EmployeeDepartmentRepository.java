package com.nhnacademy.exam.domain.employee_department.repository;

import com.nhnacademy.exam.domain.employee_department.entity.EmployeeDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface EmployeeDepartmentRepository extends JpaRepository<EmployeeDepartment, EmployeeDepartment.Pk> {
    List<EmployeeDepartment> findAllByPkDepartmentIdIn(Collection<String> departmentIds);
}
