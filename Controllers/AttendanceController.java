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

import com.example.LMS.Dtos.AttendanceResponseDTO;
import com.example.LMS.Dtos.MarkAttendanceRequestDTO;
import com.example.LMS.Dtos.UpdateAttendanceRequestDTO;
import com.example.LMS.Services.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping
    @Operation(summary = "Mark Attendance")
    @PreAuthorize("hasAnyRole('TEACHER','STAFF')")
    public ResponseEntity<AttendanceResponseDTO> markAttendance(
            @RequestBody MarkAttendanceRequestDTO dto) {
        return ResponseEntity.ok(
                attendanceService.markAttendance(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Attendance")
    @PreAuthorize("hasAnyRole('TEACHER','STAFF')")
    public ResponseEntity<AttendanceResponseDTO> updateAttendance(
            @PathVariable Integer id,
            @RequestBody UpdateAttendanceRequestDTO dto) {
        return ResponseEntity.ok(
                attendanceService.updateAttendance(id, dto));
    }

    // NOTE: "Student/Admin" — a STUDENT should only see their own history.
    // Enforce that ownership check inside attendanceService.getStudentAttendance(),
    // comparing the authenticated username to the student record linked to studentId.
    @GetMapping("/student/{studentId}")
    @Operation(summary = "Student Attendance")
    @PreAuthorize("hasAnyRole('STUDENT','ADMIN')")
    public ResponseEntity<List<AttendanceResponseDTO>> getStudentAttendance(
            @PathVariable Integer studentId) {
        return ResponseEntity.ok(
                attendanceService.getStudentAttendance(studentId));
    }

    @GetMapping("/class/{classId}")
    @Operation(summary = "Class Attendance")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public ResponseEntity<List<AttendanceResponseDTO>> getClassAttendance(
            @PathVariable Integer classId) {
        return ResponseEntity.ok(
                attendanceService.getClassAttendance(classId));
    }
}
