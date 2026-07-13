package com.example.LMS.Repositorys;

import java.util.List;

import com.example.LMS.Entity.Result;

public interface ResultRepository
        extends SMSRepository<Result, Integer> {

    List<Result> findByStudentStudentId(
            Integer studentId);

    List<Result> findByExamExamId(
            Integer examId);
}