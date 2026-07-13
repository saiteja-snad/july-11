package com.example.LMS.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.Dtos.AttendanceResponseDTO;
import com.example.LMS.Dtos.MarkAttendanceRequestDTO;
import com.example.LMS.Dtos.UpdateAttendanceRequestDTO;
import com.example.LMS.Entity.Attendance;
import com.example.LMS.Entity.Course;
import com.example.LMS.Entity.Student;
import com.example.LMS.Entity.User;
import com.example.LMS.Repositorys.AttendanceRepository;
import com.example.LMS.Repositorys.CourseRepository;
import com.example.LMS.Repositorys.StudentRepository;
import com.example.LMS.Repositorys.UserRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AttendanceResponseDTO markAttendance(
            MarkAttendanceRequestDTO dto) {

        Student student = studentRepository
                .findById(dto.getStudentId())
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        Course course = courseRepository
                .findById(dto.getCourseId())
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        User user = userRepository
                .findById(dto.getMarkedBy())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Attendance attendance = new Attendance();

        attendance.setStudent(student);
        attendance.setCourse(course);
        attendance.setAttendanceDate(dto.getAttendanceDate());
        attendance.setStatus(dto.getStatus());
        attendance.setMarkedBy(user);

        Attendance savedAttendance =
                attendanceRepository.save(attendance);

        return convertToDto(savedAttendance);
    }

    @Override
    public AttendanceResponseDTO updateAttendance(
            Integer attendanceId,
            UpdateAttendanceRequestDTO dto) {

        Attendance attendance = attendanceRepository
                .findById(attendanceId)
                .orElseThrow(() ->
                        new RuntimeException("Attendance not found"));

        User user = userRepository
                .findById(dto.getMarkedBy())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        attendance.setStatus(dto.getStatus());
        attendance.setMarkedBy(user);

        Attendance updatedAttendance =
                attendanceRepository.save(attendance);

        return convertToDto(updatedAttendance);
    }

    @Override
    public List<AttendanceResponseDTO> getStudentAttendance(
            Integer studentId) {

        return attendanceRepository
                .findByStudentStudentId(studentId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponseDTO> getClassAttendance(
            Integer classId) {

        return attendanceRepository
                .findByClassId(classId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private AttendanceResponseDTO convertToDto(
            Attendance attendance) {

        AttendanceResponseDTO dto =
                new AttendanceResponseDTO();

        dto.setAttendanceId(
                attendance.getAttendanceId());

        dto.setStudentId(
                attendance.getStudent().getStudentId());

        dto.setStudentName(
                attendance.getStudent().getFirstName()
                        + " "
                        + attendance.getStudent().getLastName());

        dto.setCourseId(
                attendance.getCourse().getCourseId());

        dto.setCourseName(
                attendance.getCourse().getCourseName());

        dto.setAttendanceDate(
                attendance.getAttendanceDate());

        dto.setStatus(
                attendance.getStatus());

        if (attendance.getMarkedBy() != null) {
            dto.setMarkedByUsername(
                    attendance.getMarkedBy().getUsername());
        }

        return dto;
    }
}