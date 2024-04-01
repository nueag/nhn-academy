package com.nhnacademy.edu.controller;


import com.nhnacademy.edu.domain.Student;
import com.nhnacademy.edu.domain.StudentRegisterRequest;
import com.nhnacademy.edu.exception.StudentNotFoundException;
import com.nhnacademy.edu.exception.ValidationFailedException;
import com.nhnacademy.edu.repository.StudentRepository;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @ModelAttribute("student")
    public Student addStudentToModel(@PathVariable("studentId") long studentId) {
        Student student = studentRepository.getStudent(studentId);
        if (Objects.isNull(student)) {
            throw new StudentNotFoundException();
        }
        return student;
    }

    @GetMapping("/{studentId}")
    public String viewStudent() {
        return "studentView";
    }

    @GetMapping(path = "/{studentId}", params = "hideScore")
    public String hideScoreViewStudent(@RequestParam("hideScore") String hideScore,
                                       Model model) {
        if (hideScore.equals("yes")) {
            model.addAttribute("hideScore", true);
        }
        return "studentView";
    }

    @GetMapping("/{studentId}/modify")
    public String studentModifyForm() {
        return "studentModify";
    }

    @PostMapping("/{studentId}/modify")
    public String modifyUser(@PathVariable("studentId") long studentId,
                             @Valid @ModelAttribute StudentRegisterRequest studentRequest,     // 수정 요청 객체
                             BindingResult bindingResult,
                             ModelMap modelmap) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        studentRequest.isCommentValid();
        Student student = new Student(studentId);
        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setScore(studentRequest.getScore());
        student.setComment(studentRequest.getComment());

        studentRepository.modify(student);
        modelmap.addAttribute("student", student);
        return "studentView";
    }

}
