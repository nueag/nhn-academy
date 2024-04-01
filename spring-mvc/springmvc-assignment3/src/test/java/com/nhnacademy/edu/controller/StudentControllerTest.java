package com.nhnacademy.edu.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.domain.Student;
import com.nhnacademy.edu.domain.StudentModifyRequest;
import com.nhnacademy.edu.domain.StudentRegisterRequest;
import com.nhnacademy.edu.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class StudentControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(new StudentsController(studentRepository)).build();
    }

    @Test
    @DisplayName("학생 조회 성공: JSON")
    void testGetStudentJson() throws Exception {
        when(studentRepository.getStudent(1L)).thenReturn(new Student(1L, "김학생", "Computer Science"));
        mockMvc.perform(get("/students/{studentId}", 1)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("학생 조회 성공: XML")
    void testGetStudentXml() throws Exception {
        when(studentRepository.getStudent(1L)).thenReturn(new Student(1L, "김학생", "Computer Science"));
        mockMvc.perform(MockMvcRequestBuilders.get("/students/{studentId}", 1)
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML));
    }

    @Test
    @DisplayName("학생 조회 실패: 존재하지 않는 번호")
    void testGetNonExistingStudent() throws Exception {
        when(studentRepository.getStudent(1L)).thenReturn(new Student(1L, "김학생", "Computer Science"));
        mockMvc.perform(MockMvcRequestBuilders.get("/students/{studentId}", 50)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("학생 등록 성공: JSON")
    void testRegisterJson() throws Exception {
        String content = objectMapper.writeValueAsString(
                new StudentRegisterRequest("gaeun", "kaeun0000@naver.com", 100, "soso"));
        when(studentRepository.register(anyString(), anyString(), anyInt(), anyString())).thenReturn(
                new Student(1L, "gaeun", "kaeun0000@naver.com"));
        MvcResult result = mockMvc.perform(post("/students")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        JSONAssert.assertEquals("{\"name\":\"gaeun\", \"email\":\"kaeun0000@naver.com\"}", jsonResponse, false);
    }

    @Test
    @DisplayName("학생 등록 성공: XML")
    void testRegisterXml() throws Exception {
        StudentRegisterRequest request = new StudentRegisterRequest("gaeun", "kaeun0000@naver.com", 100, "soso");
        when(studentRepository.register(anyString(), anyString(), anyInt(), anyString()))
                .thenReturn(new Student(1L, "gaeun", "kaeun0000@naver.com"));

        mockMvc.perform(post("/students")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andReturn();
    }

    @Test
    @DisplayName("학생 수정 성공: JSON")
    void testModifyJSON() throws Exception {
        StudentModifyRequest request = new StudentModifyRequest("gaeun", "kaeun0000@naver.com", 100, "잘하고있다!!");
        when(studentRepository.getStudent(anyLong())).thenReturn(
                new Student(1L, "geaun", "kaeun0000@naver.com", 100, "잘하고있다!!"));

        mockMvc.perform(put("/students/{studentId}", 1L)
                        .queryParam("studentId", "1")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("gaeun"))  // JSON 응답을 확인
                .andExpect(jsonPath("$.email").value("kaeun0000@naver.com"))
                .andExpect(jsonPath("$.score").value(100))
                .andExpect(jsonPath("$.comment").value("잘하고있다!!"));

    }

    @Test
    @DisplayName("학생 수정 성공: XML")
    void testModifyXml() throws Exception {
        StudentModifyRequest request = new StudentModifyRequest("gaeun", "kaeun0000@naver.com", 100, "잘하고있다!!");
        when(studentRepository.getStudent(anyLong())).thenReturn(
                new Student(1L, "geaun", "kaeun0000@naver.com", 100, "잘하고있다!!"));

        mockMvc.perform(put("/students/{studentId}", 1L)
                        .queryParam("studentId", "1")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML));
    }

    @Test
    @DisplayName("학생 수정 실패: 해당 학생이 존재하지 않는 경우")
    void testModifyFail() throws Exception {
        StudentModifyRequest request = new StudentModifyRequest("gaeun", "kaeun0000@naver.com", 100, "잘하고있다!!");
        when(studentRepository.getStudent(anyLong())).thenReturn(null);

        mockMvc.perform(put("/students/{studentId}", 1L)
                        .queryParam("studentId", "1")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isNotFound());
    }

}