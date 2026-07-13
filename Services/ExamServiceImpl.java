package com.example.LMS.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.Dtos.CreateExamRequestDTO;
import com.example.LMS.Dtos.CreateExamResponseDTO;
import com.example.LMS.Entity.Course;
import com.example.LMS.Entity.Exam;
import com.example.LMS.Repositorys.CourseRepository;
import com.example.LMS.Repositorys.ExamRepository;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void createExam(CreateExamRequestDTO dto) {

        Course course = courseRepository
                .findById(dto.getCourseId())
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        Exam exam = new Exam();

        exam.setExamName(dto.getExamName());
        exam.setCourse(course);
        exam.setExamDate(dto.getExamDate());
        exam.setTotalMarks(dto.getTotalMarks());

        examRepository.save(exam);
    }

    @Override
    public List<CreateExamResponseDTO> getAllExams() {

        return examRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CreateExamResponseDTO convertToDto(Exam exam) {

        CreateExamResponseDTO dto =
                new CreateExamResponseDTO();

        dto.setExamName(exam.getExamName());

        dto.setCourseId(
                exam.getCourse().getCourseId());

        dto.setCourseName(
                exam.getCourse().getCourseName());

        dto.setExamDate(
                exam.getExamDate());

        dto.setTotalMarks(
                exam.getTotalMarks());

        return dto;
    }
}