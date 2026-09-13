package com.example.DTOCrudDemo.controller;

import com.example.DTOCrudDemo.dto.createStudentDtoRequest;
import com.example.DTOCrudDemo.dto.createStudentDtoResponse;
import com.example.DTOCrudDemo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<createStudentDtoResponse> create(@RequestBody createStudentDtoRequest request){
        createStudentDtoResponse response = studentService.create(request);
        return ResponseEntity.ok(response);
    }

}
