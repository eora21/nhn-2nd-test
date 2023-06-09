package com.nhnacademy.exam.domain.employee_department.entity;

import com.nhnacademy.exam.domain.department.entity.Department;
import com.nhnacademy.exam.domain.employee.entity.Employee;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "employee_department")
public class EmployeeDepartment {
    @EmbeddedId
    private Pk pk;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        @ManyToOne
        @JoinColumn(name = "employee_id")
        private Employee employee;

        @ManyToOne
        @JoinColumn(name = "department_id")
        private Department department;
    }
}
