package com.example.SpringBootCrudDemo.Repository;

import com.example.SpringBootCrudDemo.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    public Optional<Student> findByIdAndIsDeleted(Integer id, Boolean isDeleted);
}
