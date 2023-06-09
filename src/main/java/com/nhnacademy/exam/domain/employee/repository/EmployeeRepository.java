package com.nhnacademy.exam.domain.employee.repository;

import com.nhnacademy.exam.domain.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
