package com.example.LMS.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.Dtos.CourseResponseDTO;
import com.example.LMS.Dtos.CreateCourseRequestDTO;
import com.example.LMS.Dtos.UpdateCourseRequestDTO;
import com.example.LMS.Entity.Course;
import com.example.LMS.Entity.Department;
import com.example.LMS.Repositorys.CourseRepository;
import com.example.LMS.Repositorys.DepartmentRepository;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public CourseResponseDTO createCourse(CreateCourseRequestDTO dto) {

        Department department = departmentRepository
                .findById(dto.getDepartmentId())
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));

        Course course = new Course();

        course.setCourseCode(dto.getCourseCode());
        course.setCourseName(dto.getCourseName());
        course.setDescription(dto.getDescription());
        course.setCredits(dto.getCredits());
        course.setDepartment(department);

        Course savedCourse = courseRepository.save(course);

        return mapToDto(savedCourse);
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDTO getCourseById(
            Integer courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        return mapToDto(course);
    }

    @Override
    public CourseResponseDTO updateCourse(
            Integer courseId,
            UpdateCourseRequestDTO dto) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        Department department = departmentRepository
                .findById(dto.getDepartmentId())
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));

        course.setCourseName(dto.getCourseName());
        course.setDescription(dto.getDescription());
        course.setCredits(dto.getCredits());
        course.setDepartment(department);

        Course updatedCourse = courseRepository.save(course);

        return mapToDto(updatedCourse);
    }

    private CourseResponseDTO mapToDto(Course course) {

        CourseResponseDTO dto = new CourseResponseDTO();

        dto.setCourseId(course.getCourseId());
        dto.setCourseCode(course.getCourseCode());
        dto.setCourseName(course.getCourseName());
        dto.setDescription(course.getDescription());
        dto.setCredits(course.getCredits());

        if (course.getDepartment() != null) {
            dto.setDepartmentId(
                    course.getDepartment().getDepartmentId());

            dto.setDepartmentName(
                    course.getDepartment().getDepartmentName());
        }

        return dto;
    }


}
