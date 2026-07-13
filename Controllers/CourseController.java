package com.example.LMS.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LMS.Dtos.CourseResponseDTO;
import com.example.LMS.Dtos.CreateCourseRequestDTO;
import com.example.LMS.Dtos.UpdateCourseRequestDTO;
import com.example.LMS.Services.CourseService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    @Operation(summary = "Create Course")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseResponseDTO> createCourse(
            @RequestBody CreateCourseRequestDTO dto) {
        return ResponseEntity.ok(
                courseService.createCourse(dto));
    }

    // All authenticated users (Admin/Staff/Student) may view courses
    @GetMapping
    @Operation(summary = "Get All Courses")
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        return ResponseEntity.ok(
                courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Course By Id")
    public ResponseEntity<CourseResponseDTO> getCourse(@PathVariable Integer id) {
        return ResponseEntity.ok(
                courseService.getCourseById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Course")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseResponseDTO> updateCourse(
            @PathVariable Integer id,
            @RequestBody UpdateCourseRequestDTO dto) {
        return ResponseEntity.ok(
                courseService.updateCourse(id, dto));
    }
}
