package com.example.LMS.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.Entity.Department;
import com.example.LMS.Repositorys.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(Department department) {

        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {

        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {

        return departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));
    }

    @Override
    public Department updateDepartment(
            Integer departmentId,
            Department department) {

        Department existingDepartment =
                departmentRepository.findById(departmentId)
                        .orElseThrow(() ->
                                new RuntimeException("Department not found"));

        existingDepartment.setDepartmentName(
                department.getDepartmentName());

        existingDepartment.setHodName(
                department.getHodName());

        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(Integer departmentId) {

        Department existingDepartment =
                departmentRepository.findById(departmentId)
                        .orElseThrow(() ->
                                new RuntimeException("Department not found"));

        departmentRepository.delete(existingDepartment);
    }
}