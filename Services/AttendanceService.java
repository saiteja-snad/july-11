package com.example.LMS.Services;



import java.util.List;

import com.example.LMS.Dtos.AttendanceResponseDTO;
import com.example.LMS.Dtos.MarkAttendanceRequestDTO;
import com.example.LMS.Dtos.UpdateAttendanceRequestDTO;

public interface AttendanceService {

   AttendanceResponseDTO markAttendance( MarkAttendanceRequestDTO dto);

   AttendanceResponseDTO updateAttendance(Integer attendanceId,UpdateAttendanceRequestDTO dto);

    List<AttendanceResponseDTO> getStudentAttendance(Integer studentId);

    List<AttendanceResponseDTO> getClassAttendance( Integer classId);
}
