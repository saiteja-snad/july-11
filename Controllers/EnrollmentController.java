package com.example.LMS.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LMS.Dtos.CreateEnrollmentRequestDTO;
import com.example.LMS.Dtos.EnrollmentResponseDTO;
import com.example.LMS.Services.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createEnrollment(
            @RequestBody CreateEnrollmentRequestDTO dto) {
        enrollmentService.createEnrollment(dto);
        return ResponseEntity.ok("Enrollment Created");
    }

    // NOTE: "Student/Admin" — enforce ownership (studentId belongs to caller)
    // inside enrollmentService.getStudentEnrollments() for the STUDENT role.
    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('STUDENT','ADMIN')")
    public ResponseEntity<List<EnrollmentResponseDTO>> getStudentEnrollments(
            @PathVariable Integer studentId) {
        return ResponseEntity.ok(
                enrollmentService.getStudentEnrollments(studentId));
    }

    @GetMapping("/course/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseEntity<List<EnrollmentResponseDTO>> getCourseEnrollments(
            @PathVariable Integer courseId) {
        return ResponseEntity.ok(
                enrollmentService.getCourseEnrollments(courseId));
    }
}
