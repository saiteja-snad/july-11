package com.example.LMS.Services;



import java.util.List;

import com.example.LMS.Dtos.CreateStudentRequestDTO;
import com.example.LMS.Dtos.StudentResponseDTO;
import com.example.LMS.Dtos.UpdateStudentRequestDTO;

public interface StudentService {StudentResponseDTO createStudent(CreateStudentRequestDTO dto);

    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO getStudentById(Integer studentId);

    StudentResponseDTO updateStudent( Integer studentId,UpdateStudentRequestDTO dto);

    void changeStudentStatus(Integer studentId,String status);
}