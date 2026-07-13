package com.example.LMS.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LMS.Dtos.CreateStudentRequestDTO;
import com.example.LMS.Dtos.StudentResponseDTO;
import com.example.LMS.Dtos.UpdateStudentRequestDTO;
import com.example.LMS.Services.StudentService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Create Student")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(
            @RequestBody CreateStudentRequestDTO dto) {
        return ResponseEntity.ok(
                studentService.createStudent(dto));
    }

    @Operation(summary = "Get All Students")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        return ResponseEntity.ok(
                studentService.getAllStudents());
    }

    // NOTE: "Admin or Owner" — this is not a plain role check. Enforce
    // ownership inside studentService.getStudentById(): allow if caller has
    // ROLE_ADMIN, OR if the authenticated user's linked student_id == id.
    // Throw a 403 from the service otherwise. @PreAuthorize alone can't
    // express "owner" without exposing the student_id on the JWT principal.
    @Operation(summary = "Get Student By Id")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                studentService.getStudentById(id));
    }

    @Operation(summary = "Update Student")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable Integer id,
            @RequestBody UpdateStudentRequestDTO dto) {
        return ResponseEntity.ok(
                studentService.updateStudent(id, dto));
    }

    @Operation(summary = "Change Student Status")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> changeStatus(
            @PathVariable Integer id,
            @RequestParam String status) {
        studentService.changeStudentStatus(id, status);
        return ResponseEntity.ok("Status Updated");
    }
}
