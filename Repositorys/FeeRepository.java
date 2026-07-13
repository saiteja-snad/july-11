package com.example.LMS.Repositorys;


import java.util.List;

import com.example.LMS.Entity.Fee;

public interface FeeRepository
        extends SMSRepository<Fee, Integer> {

    List<Fee> findByStudentStudentId(Integer studentId);

    List<Fee> findByStatus(String status);
}