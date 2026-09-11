package com.example.SpringBootCrudDemo.Controller;

import com.example.SpringBootCrudDemo.Entities.Student;
import com.example.SpringBootCrudDemo.Services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    private StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student stundetRes = studentService.createStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(stundetRes);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id){
        Student studentRes = studentService.getStudent(id);
        if(studentRes == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentRes);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> allStudents = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(allStudents);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student student){
        Student studentRes = studentService.updateStudent(id, student);
        if(studentRes == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentRes);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id){
        Boolean isDeleted = studentService.deleteStudent(id);
        if(!isDeleted) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record Not Found");
        return ResponseEntity.status(HttpStatus.OK).body("Record Deleted");
    }

    @PatchMapping("/soft-delete/{id}")
    public ResponseEntity<String> deleteStudentSoft(@PathVariable Integer id){
        boolean isDeleted = studentService.deleteStudentSoftly(id);
        if(isDeleted) return ResponseEntity.ok().body("Record Deleted");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record Not Found");
    }
}

