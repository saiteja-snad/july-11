package com.example.LMS.Repositorys;



import java.util.List;
import java.util.Optional;

import com.example.LMS.Entity.Course;

public interface CourseRepository extends SMSRepository<Course, Integer> {

    Optional<Course> findByCourseCode(String courseCode);

    boolean existsByCourseCode(String courseCode);

    List<Course> findByDepartmentDepartmentId(Integer departmentId);
}