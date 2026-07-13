package com.example.LMS.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LMS.Dtos.CreateExamRequestDTO;
import com.example.LMS.Dtos.CreateExamResponseDTO;
import com.example.LMS.Services.ExamService;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<String> createExam(
            @RequestBody CreateExamRequestDTO dto) {
        examService.createExam(dto);
        return ResponseEntity.ok("Exam Created");
    }

    // Any authenticated user may view exam listings
    @GetMapping
    public ResponseEntity<List<CreateExamResponseDTO>> getAllExams() {
        return ResponseEntity.ok(
                examService.getAllExams());
    }
}
