package com.nhnacademy.exam.domain.employee_department.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.domain.department.entity.Department;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class EmployeeDepartmentMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("정상 요청")
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/department-members")
                        .param("departmentIds", "T1,T2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-USER-ID", "nhnacademy"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("헤더 에러")
    void wrongHeader() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/department-members")
                        .param("departmentIds", "T1,T2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-USER-ID", "eora21"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("헤더 누락 에러")
    void noHeader() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/department-members")
                        .param("departmentIds", "T1,T2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("빈 파라미터 에러")
    void wrongParameter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/department-members")
                        .param("departmentIds", "     ")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-USER-ID", "nhnacademy"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("파라미터 누락 에러")
    void noParameter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/department-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-USER-ID", "nhnacademy"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("accept 요청 에러")
    void wrongAccept() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/department-members")
                        .contentType(MediaType.APPLICATION_CBOR)
                        .header("X-USER-ID", "nhnacademy"))
                .andExpect(status().isBadRequest());
    }
}