package com.example.LMS.Services;


import java.util.List;

import com.example.LMS.Dtos.PublishResultRequestDTO;
import com.example.LMS.Dtos.ResultResponseDTO;

public interface ResultService {

    void publishResult(PublishResultRequestDTO dto);

    List<ResultResponseDTO> getStudentResults(Integer studentId);
    List<ResultResponseDTO> getExamResults(Integer examId);
}
