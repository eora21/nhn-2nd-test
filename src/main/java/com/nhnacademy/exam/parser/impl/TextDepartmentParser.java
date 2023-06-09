package com.nhnacademy.exam.parser.impl;

import com.nhnacademy.exam.domain.employee_department.model.normal.EmployeeDepartmentDto;
import com.nhnacademy.exam.parser.DepartmentParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TextDepartmentParser implements DepartmentParser {

    @Override
    public String getFileType() {
        return "txt";
    }

    @Override
    public List<EmployeeDepartmentDto> parsing(File file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            List<String[]> collect = bufferedReader.lines()
                    .skip(3)
                    .map(line -> line.split("\\|"))
                    .map(line -> Arrays.copyOfRange(line, 1, line.length))
                    .collect(Collectors.toList());
            collect.remove(collect.size() - 1);

            return collect.stream()
                    .map(EmployeeDepartmentDto::toDto)
                    .collect(Collectors.toList());
        }
    }
}
