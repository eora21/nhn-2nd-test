package com.nhnacademy.exam.domain.department.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.domain.department.entity.Department;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class DepartmentMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("정상 요청")
    void create() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/departments")
                        .content(objectMapper.writeValueAsString(new Department("T1", "TEST")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-USER-ID", "nhnacademy"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("헤더 에러")
    void wrongHeader() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/departments")
                        .content(objectMapper.writeValueAsString(new Department("T1", "TEST")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-USER-ID", "eora21"))
                .andExpect(status().isUnauthorized());
    }
}