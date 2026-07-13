package com.example.LMS.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.Entity.ClassEntity;
import com.example.LMS.Repositorys.ClassRepository;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Override
    public ClassEntity createClass(ClassEntity classEntity) {

        return classRepository.save(classEntity);
    }

    @Override
    public List<ClassEntity> getAllClasses() {

        return classRepository.findAll();
    }

    @Override
    public ClassEntity getClassById(Integer classId) {

        return classRepository.findById(classId)
        .orElseThrow(() ->new RuntimeException("Class not found"));
    }

    @Override
    public ClassEntity updateClass(Integer classId, ClassEntity classEntity) {

        ClassEntity existingClass = classRepository.findById(classId) .orElseThrow(() ->new RuntimeException("Class not found"));

        existingClass.setClassName(classEntity.getClassName());

        existingClass.setSectionName( classEntity.getSectionName());

        existingClass.setDepartment(classEntity.getDepartment());

        return classRepository.save(existingClass);
    }

    @Override
    public void deleteClass(Integer classId) {

        ClassEntity existingClass = classRepository
                .findById(classId)
                .orElseThrow(() ->new RuntimeException("Class not found"));
        classRepository.delete(existingClass);
    }
}