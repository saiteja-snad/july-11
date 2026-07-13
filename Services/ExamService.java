package com.example.LMS.Services;


import java.util.List;

import com.example.LMS.Dtos.CreateExamRequestDTO;
import com.example.LMS.Dtos.CreateExamResponseDTO;

public interface ExamService {

    void createExam(CreateExamRequestDTO dto);

    List<CreateExamResponseDTO> getAllExams();
}