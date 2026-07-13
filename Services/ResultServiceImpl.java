package com.example.LMS.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.Dtos.PublishResultRequestDTO;
import com.example.LMS.Dtos.ResultResponseDTO;
import com.example.LMS.Entity.Exam;
import com.example.LMS.Entity.Result;
import com.example.LMS.Entity.Student;
import com.example.LMS.Repositorys.ExamRepository;
import com.example.LMS.Repositorys.ResultRepository;
import com.example.LMS.Repositorys.StudentRepository;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExamRepository examRepository;

    @Override
    public void publishResult(PublishResultRequestDTO dto) {

        Student student = studentRepository
                .findById(dto.getStudentId())
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        Exam exam = examRepository
                .findById(dto.getExamId())
                .orElseThrow(() ->
                        new RuntimeException("Exam not found"));

        Result result = new Result();

        result.setStudent(student);
        result.setExam(exam);
        result.setMarksObtained(dto.getMarksObtained());
        result.setGrade(dto.getGrade());
        result.setRemarks(dto.getRemarks());
        result.setPublishedAt(LocalDateTime.now());

        resultRepository.save(result);
    }

    @Override
    public List<ResultResponseDTO> getStudentResults(
            Integer studentId) {

        return resultRepository
                .findByStudentStudentId(studentId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResultResponseDTO> getExamResults(
            Integer examId) {

        return resultRepository
                .findByExamExamId(examId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ResultResponseDTO convertToDto(Result result) {

        ResultResponseDTO dto = new ResultResponseDTO();

        dto.setResultId(result.getResultId());

        dto.setStudentId(
                result.getStudent().getStudentId());

        dto.setExamId(
                result.getExam().getExamId());

        dto.setStudentName(
                result.getStudent().getFirstName()
                        + " "
                        + result.getStudent().getLastName());

        dto.setExamName(
                result.getExam().getExamName());

        dto.setCourseName(
                result.getExam()
                      .getCourse()
                      .getCourseName());

        dto.setTotalMarks(
                result.getExam()
                      .getTotalMarks());

        dto.setMarksObtained(
                result.getMarksObtained());

        dto.setGrade(
                result.getGrade());

        dto.setRemarks(
                result.getRemarks());

        dto.setPublishedAt(
                result.getPublishedAt());

        return dto;
    }
}