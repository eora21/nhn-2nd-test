package com.nhnacademy.exam.domain.department.repository;

import com.nhnacademy.exam.domain.department.entity.Department;
import com.nhnacademy.exam.domain.department.model.response.DepartmentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class DepartmentRepositoryTest {
    @Autowired
    DepartmentRepository departmentRepository;

    @BeforeEach
    void insertData() {
        departmentRepository.saveAll(Set.of(
                new Department("T1", "테스트부서1"),
                new Department("T2", "테스트부서2")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"T1", "T2"})
    @DisplayName("dto 정상 반환 테스트")
    void findDtoById(String id) {
        Optional<DepartmentResponseDto> dtoById = departmentRepository.findDtoById(id);
        assertThat(dtoById).isNotEmpty();
        assertThat(dtoById.get()).isInstanceOf(DepartmentResponseDto.class);
    }
}