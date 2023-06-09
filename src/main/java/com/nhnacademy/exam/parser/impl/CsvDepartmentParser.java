package com.nhnacademy.exam.parser.impl;

import com.nhnacademy.exam.domain.employee_department.model.normal.EmployeeDepartmentDto;
import com.nhnacademy.exam.parser.DepartmentParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CsvDepartmentParser implements DepartmentParser {

    @Override
    public String getFileType() {
        return "csv";
    }

    @Override
    public List<EmployeeDepartmentDto> parsing(File file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            return bufferedReader.lines()
                    .skip(1)
                    .filter(line -> line.trim().length() > 0)
                    .map(line -> line.split(","))
                    .map(EmployeeDepartmentDto::toDto)
                    .collect(Collectors.toList());
        }
    }
}
