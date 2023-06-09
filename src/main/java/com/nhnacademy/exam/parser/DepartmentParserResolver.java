package com.nhnacademy.exam.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DepartmentParserResolver {
    private final List<DepartmentParser> departmentParserList;

    public DepartmentParser getDepartmentParser(String fileName) {
        return departmentParserList.stream()
                .filter(departmentParser -> departmentParser.matchFileType(fileName))
                .findAny()
                .orElse(null);
    }

}
