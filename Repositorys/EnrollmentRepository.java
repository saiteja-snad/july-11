package com.example.LMS.Repositorys;


import java.util.List;

import com.example.LMS.Entity.Enrollment;

public interface EnrollmentRepository
        extends SMSRepository<Enrollment, Integer> {

    List<Enrollment> findByStudentStudentId(Integer studentId);

    List<Enrollment> findByCourseCourseId(Integer courseId);
}