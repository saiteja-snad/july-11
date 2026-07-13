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

import com.example.LMS.Dtos.PublishResultRequestDTO;
import com.example.LMS.Dtos.ResultResponseDTO;
import com.example.LMS.Services.ResultService;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> publishResult(
            @RequestBody PublishResultRequestDTO dto) {
        resultService.publishResult(dto);
        return ResponseEntity.ok("Result Published");
    }

    // NOTE: "Student/Admin" — enforce ownership (studentId belongs to caller)
    // inside resultService.getStudentResults() for the STUDENT role.
    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('STUDENT','ADMIN')")
    public ResponseEntity<List<ResultResponseDTO>> getStudentResults(
            @PathVariable Integer studentId) {
        return ResponseEntity.ok(
                resultService.getStudentResults(studentId));
    }

    @GetMapping("/exam/{examId}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<List<ResultResponseDTO>> getExamResults(
            @PathVariable Integer examId) {
        return ResponseEntity.ok(
                resultService.getExamResults(examId));
    }
}
