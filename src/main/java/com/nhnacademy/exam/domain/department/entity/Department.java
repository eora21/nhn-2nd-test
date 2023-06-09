package com.nhnacademy.exam.domain.department.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "departments")
public class Department implements Serializable {
    @Id
    private String id;

    @Setter
    private String name;
}
