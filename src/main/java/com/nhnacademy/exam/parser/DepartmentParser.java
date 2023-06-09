package com.nhnacademy.exam.parser;

import com.nhnacademy.exam.domain.employee_department.model.normal.EmployeeDepartmentDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface DepartmentParser {
     String getFileType();
     List<EmployeeDepartmentDto> parsing (File file) throws IOException;
     default boolean matchFileType(String fileName){
          return fileName.trim().toLowerCase().endsWith(getFileType().toLowerCase());
     }
}
