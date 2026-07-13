package com.example.LMS.Repositorys;


import java.util.List;

import com.example.LMS.Entity.ClassEntity;

public interface ClassRepository
        extends SMSRepository<ClassEntity, Integer> {

    List<ClassEntity> findByDepartmentDepartmentId(Integer departmentId);
}