package com.example.LMS.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.Dtos.CreateStudentRequestDTO;
import com.example.LMS.Dtos.StudentResponseDTO;
import com.example.LMS.Dtos.UpdateStudentRequestDTO;
import com.example.LMS.Entity.ClassEntity;
import com.example.LMS.Entity.Role;
import com.example.LMS.Entity.Student;
import com.example.LMS.Entity.User;
import com.example.LMS.Repositorys.ClassRepository;
import com.example.LMS.Repositorys.RoleRepository;
import com.example.LMS.Repositorys.StudentRepository;
import com.example.LMS.Repositorys.UserRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public StudentResponseDTO createStudent(
            CreateStudentRequestDTO dto) {

        Role role = roleRepository.findByRoleName("STUDENT")
                .orElseThrow(() ->
                        new RuntimeException("Student role not found"));

        ClassEntity classEntity = classRepository
                .findById(dto.getClassId())
                .orElseThrow(() ->
                        new RuntimeException("Class not found"));

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPasswordHash(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setRole(role);
        user.setStatus("ACTIVE");
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        Student student = new Student();

        student.setStudentCode(dto.getStudentCode());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setDob(dto.getDob());
        student.setGender(dto.getGender());
        student.setPhone(dto.getPhone());
        student.setEmail(dto.getEmail());
        student.setAddress(dto.getAddress());

        // Admission Date
        student.setAdmissionDate(LocalDate.now());

        student.setStatus("ACTIVE");
        student.setUser(savedUser);
        student.setClassEntity(classEntity);

        Student savedStudent = studentRepository.save(student);

        return convertToDto(savedStudent);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO getStudentById(
            Integer studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        return convertToDto(student);
    }

    @Override
    public StudentResponseDTO updateStudent(
            Integer studentId,
            UpdateStudentRequestDTO dto) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        ClassEntity classEntity = classRepository
                .findById(dto.getClassId())
                .orElseThrow(() ->
                        new RuntimeException("Class not found"));

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setDob(dto.getDob());
        student.setGender(dto.getGender());
        student.setPhone(dto.getPhone());
        student.setAddress(dto.getAddress());
        student.setStatus(dto.getStatus());
        student.setClassEntity(classEntity);

        Student updatedStudent = studentRepository.save(student);

        return convertToDto(updatedStudent);
    }

    @Override
    public void changeStudentStatus(
            Integer studentId,
            String status) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        student.setStatus(status);

        studentRepository.save(student);
    }

    private StudentResponseDTO convertToDto(Student student) {

        StudentResponseDTO dto = new StudentResponseDTO();

        dto.setStudentId(student.getStudentId());
        dto.setStudentCode(student.getStudentCode());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setDob(student.getDob());
        dto.setGender(student.getGender());
        dto.setPhone(student.getPhone());
        dto.setEmail(student.getEmail());
        dto.setAddress(student.getAddress());
        dto.setStatus(student.getStatus());

        if (student.getClassEntity() != null) {

            dto.setClassName(
                    student.getClassEntity().getClassName());

            dto.setSectionName(
                    student.getClassEntity().getSectionName());
        }

        return dto;
    }
}