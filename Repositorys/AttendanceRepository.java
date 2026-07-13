package com.example.LMS.Repositorys;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.LMS.Entity.Attendance;

public interface AttendanceRepository
        extends SMSRepository<Attendance, Integer> {

    List<Attendance> findByStudentStudentId(Integer studentId);

    List<Attendance> findByCourseCourseId(Integer courseId);

    List<Attendance> findByAttendanceDate(LocalDate attendanceDate);

    boolean existsByStudentStudentIdAndCourseCourseIdAndAttendanceDate(
            Integer studentId,
            Integer courseId,
            LocalDate attendanceDate
    );

    @Query("""
           SELECT a
           FROM Attendance a
           WHERE a.student.classEntity.classId = :classId
           """)
    List<Attendance> findByClassId(
            @Param("classId") Integer classId);
}