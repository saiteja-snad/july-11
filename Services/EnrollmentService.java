package com.example.LMS.Services;



import java.util.List;

import com.example.LMS.Dtos.CreateEnrollmentRequestDTO;
import com.example.LMS.Dtos.EnrollmentResponseDTO;

public interface EnrollmentService {


void createEnrollment(CreateEnrollmentRequestDTO dto);

    List<EnrollmentResponseDTO> getStudentEnrollments(Integer studentId);

    List<EnrollmentResponseDTO> getCourseEnrollments(Integer courseId);

    EnrollmentResponseDTO getEnrollmentById( Integer enrollmentId);

    boolean updateEnrollment(Integer enrollmentId,CreateEnrollmentRequestDTO dto);

}