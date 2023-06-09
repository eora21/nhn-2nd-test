package com.nhnacademy.exam.parser.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.domain.employee_department.model.normal.EmployeeDepartmentDto;
import com.nhnacademy.exam.parser.DepartmentParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class JsonDepartmentParser implements DepartmentParser {

    private final ObjectMapper objectMapper;
    @Override
    public String getFileType() {
        return "json";
    }

    @Override
    public List<EmployeeDepartmentDto> parsing(File file) throws IOException {
        return Arrays.stream(objectMapper.readValue(file, Map[].class))
                .map(EmployeeDepartmentDto::toDto)
                .collect(Collectors.toList());
    }
}
