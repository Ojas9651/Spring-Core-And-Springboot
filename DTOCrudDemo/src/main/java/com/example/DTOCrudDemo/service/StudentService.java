package com.example.DTOCrudDemo.service;

import com.example.DTOCrudDemo.dto.createStudentDtoRequest;
import com.example.DTOCrudDemo.dto.createStudentDtoResponse;
import com.example.DTOCrudDemo.entity.Student;
import com.example.DTOCrudDemo.repository.StudentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public createStudentDtoResponse create(createStudentDtoRequest request){

        Student student = mapDtoToStudent(request);

        Student studentRes = studentRepository.save(student);

        createStudentDtoResponse studentDtoRes = mapStudentToDto(studentRes);

        return studentDtoRes;
    }

    public Student mapDtoToStudent(createStudentDtoRequest req){
        Student student = new Student();
        student.setName(req.getName());
        student.setEmail(req.getEmail());
        student.setMobileNo(req.getMobileNo());
        student.setRollNo(req.getRollNo());
        student.setSubject(req.getSubject());
        student.setDeleted(false);
        student.setCreatedAt(LocalDateTime.now());
        return student;
    }

    public createStudentDtoResponse mapStudentToDto(Student student){
        createStudentDtoResponse response = new createStudentDtoResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setMobileNo(student.getMobileNo());
        response.setRollNo(student.getRollNo());
        response.setSubject(student.getSubject());
        response.setCreatedAt(student.getCreatedAt());
        return response;
    }
}
