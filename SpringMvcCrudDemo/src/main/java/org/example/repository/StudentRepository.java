package org.example.repository;

import org.example.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    Map<Long, Student> studentDB;

    public StudentRepository(){
        studentDB = new HashMap<>();
    }

    public Student save(Student student){
        studentDB.put(student.getId(), student);
        return student;
    }

    public Student findById(Long id){
        return studentDB.getOrDefault(id, null);
    }

    public List<Student> findAll(){
        List<Student> students = new LinkedList<>();
        for(Student student : studentDB.values()){
            students.add(student);
        }
        return students;
    }
}
