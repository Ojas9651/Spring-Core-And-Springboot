package com.example.SpringBootCrudDemo.Services;

import com.example.SpringBootCrudDemo.Entities.Student;
import com.example.SpringBootCrudDemo.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    private StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq){
        studentReq.setDeleted(false);
        Student studentRes = studentRepository.save(studentReq);
        return studentRes;
    }

    public Student getStudent(Integer id){
        Optional<Student> studentRes = studentRepository.findByIdAndIsDeleted(id, false);
        if(studentRes.isPresent()) return studentRes.get();
        return null;
    }

    public List<Student> getAllStudents(){
        List<Student> allStudent = studentRepository.findAll();
        List<Student> ignoreSoftDeletedStudents = new LinkedList<>();
        for(Student st : allStudent){
            if(!st.isDeleted()) ignoreSoftDeletedStudents.add(st);
        }
        return ignoreSoftDeletedStudents;
    }

    public Student updateStudent(Integer id, Student student){
        Optional<Student> studentRes = studentRepository.findById(id);
        if(studentRes.isPresent() && studentRes.get().isDeleted() == true) return null;
        if(studentRes.isEmpty()) return null;
        Student studentToSave = studentRes.get();
        studentToSave.setRollNo(student.getRollNo());
        studentToSave.setEmail(student.getEmail());
        studentToSave.setName(student.getName());
        studentToSave.setAge(student.getAge());
        studentToSave.setSubject(student.getSubject());
        studentToSave.setId(student.getId());
        Student savedStudentRes = studentRepository.save(studentToSave);
        return savedStudentRes;
    }

    public boolean deleteStudent(Integer id){
        Optional<Student> studentRes = studentRepository.findById(id);
        if(studentRes.isEmpty()) return false;
        studentRepository.delete(studentRes.get());
        return true;
    }

    public boolean deleteStudentSoftly(Integer id){
        Optional<Student> studentRes = studentRepository.findById(id);
        if(studentRes.isEmpty()) return false;
        if(studentRes.isPresent() && studentRes.get().isDeleted() == true) return false;

        Student student = studentRes.get();
        student.setDeleted(true);
        studentRepository.save(student);
        return true;
    }
}
