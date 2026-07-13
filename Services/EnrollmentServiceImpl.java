package com.example.LMS.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.Dtos.CreateEnrollmentRequestDTO;
import com.example.LMS.Dtos.EnrollmentResponseDTO;
import com.example.LMS.Entity.Course;
import com.example.LMS.Entity.Enrollment;
import com.example.LMS.Entity.Student;
import com.example.LMS.Repositorys.CourseRepository;
import com.example.LMS.Repositorys.EnrollmentRepository;
import com.example.LMS.Repositorys.StudentRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void createEnrollment(CreateEnrollmentRequestDTO dto) {

        Student student = studentRepository
                .findById(dto.getStudentId())
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        Course course = courseRepository
                .findById(dto.getCourseId())
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setAcademicYear(dto.getAcademicYear());
        enrollment.setSemester(dto.getSemester());
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setStatus("ACTIVE");

        enrollmentRepository.save(enrollment);
    }

    @Override
    public List<EnrollmentResponseDTO> getStudentEnrollments(
            Integer studentId) {

        return enrollmentRepository
                .findByStudentStudentId(studentId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentResponseDTO> getCourseEnrollments(
            Integer courseId) {

        return enrollmentRepository
                .findByCourseCourseId(courseId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentResponseDTO getEnrollmentById(
            Integer enrollmentId) {

        Enrollment enrollment = enrollmentRepository
                .findById(enrollmentId)
                .orElseThrow(() ->
                        new RuntimeException("Enrollment not found"));

        return convertToDto(enrollment);
    }

    @Override
    public boolean updateEnrollment(
            Integer enrollmentId,
            CreateEnrollmentRequestDTO dto) {

        Enrollment enrollment = enrollmentRepository
                .findById(enrollmentId)
                .orElseThrow(() ->
                        new RuntimeException("Enrollment not found"));

        Student student = studentRepository
                .findById(dto.getStudentId())
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        Course course = courseRepository
                .findById(dto.getCourseId())
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setAcademicYear(dto.getAcademicYear());
        enrollment.setSemester(dto.getSemester());

        enrollmentRepository.save(enrollment);

        return true;
    }

    private EnrollmentResponseDTO convertToDto(
            Enrollment enrollment) {

        EnrollmentResponseDTO dto =
                new EnrollmentResponseDTO();

        dto.setEnrollmentId(enrollment.getEnrollmentId());

        dto.setStudentId(
                enrollment.getStudent().getStudentId());

        dto.setCourseId(
                enrollment.getCourse().getCourseId());

        dto.setStudentName(
                enrollment.getStudent().getFirstName()
                        + " "
                        + enrollment.getStudent().getLastName());

        dto.setCourseName(
                enrollment.getCourse().getCourseName());

        dto.setAcademicYear(
                enrollment.getAcademicYear());

        dto.setSemester(
                enrollment.getSemester());

        dto.setEnrollmentDate(
                enrollment.getEnrollmentDate());

        dto.setStatus(
                enrollment.getStatus());

        return dto;
    }
}