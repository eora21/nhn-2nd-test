package com.nhnacademy.exam.domain.employee.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Entity
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    private Long id;

    private String name;
}
