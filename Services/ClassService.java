package com.example.LMS.Services;



import java.util.List;

import com.example.LMS.Entity.ClassEntity;

public interface ClassService {

    ClassEntity createClass(ClassEntity classEntity);

    List<ClassEntity> getAllClasses();

    ClassEntity getClassById(Integer classId);

    ClassEntity updateClass(Integer classId,ClassEntity classEntity);

    void deleteClass(Integer classId);
}