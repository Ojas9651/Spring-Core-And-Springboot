package org.example.controller;

import org.example.entity.Student;
import org.example.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student studentRes = studentService.createStudent(student);
        return ResponseEntity.ok(studentRes);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long id){
        Student studentRes = studentService.getStudent(id);
        if(studentRes == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(studentRes);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
}
