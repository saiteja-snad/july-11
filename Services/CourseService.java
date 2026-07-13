package com.example.LMS.Services;



import java.util.List;

import com.example.LMS.Dtos.CourseResponseDTO;
import com.example.LMS.Dtos.CreateCourseRequestDTO;
import com.example.LMS.Dtos.UpdateCourseRequestDTO;

public interface CourseService {CourseResponseDTO createCourse(CreateCourseRequestDTO dto);

    List<CourseResponseDTO> getAllCourses();

    CourseResponseDTO getCourseById(Integer courseId);

    CourseResponseDTO updateCourse(Integer courseId,UpdateCourseRequestDTO dto);
}