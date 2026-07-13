package com.example.LMS.Repositorys;


import java.util.List;

import com.example.LMS.Entity.Exam;

public interface ExamRepository
        extends SMSRepository<Exam, Integer> {

    List<Exam> findByCourseCourseId(Integer courseId);
}