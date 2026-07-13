package com.example.LMS.Repositorys;



import java.util.Optional;

import com.example.LMS.Entity.Student;

public interface StudentRepository extends SMSRepository<Student, Integer> {

    Optional<Student> findByStudentCode(String studentCode);

    boolean existsByStudentCode(String studentCode);
}