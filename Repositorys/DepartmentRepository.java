package com.example.LMS.Repositorys;



import java.util.Optional;

import com.example.LMS.Entity.Department;

public interface DepartmentRepository
        extends SMSRepository<Department, Integer> {

    Optional<Department> findByDepartmentName(String departmentName);

    boolean existsByDepartmentName(String departmentName);
}