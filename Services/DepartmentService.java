package com.example.LMS.Services;



import java.util.List;

import com.example.LMS.Entity.Department;

public interface DepartmentService {


Department createDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentById(Integer departmentId);

    Department updateDepartment(Integer departmentId,Department department);

    void deleteDepartment(Integer departmentId);

}