package com.example.LMS.Dtos;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarkAttendanceRequestDTO {

    @NotNull(message = "Student ID is required")
    @Positive(message = "Student ID must be greater than 0")
    private Integer studentId;

    @NotNull(message = "Course ID is required")
    @Positive(message = "Course ID must be greater than 0")
    private Integer courseId;

    @NotNull(message = "Attendance date is required")
    private LocalDate attendanceDate;

    @NotBlank(message = "Status is required")
    @Pattern(
        regexp = "PRESENT|ABSENT|LATE",
        message = "Status must be PRESENT, ABSENT, or LATE"
    )
    private String status;

    @NotNull(message = "Marked By is required")
    @Positive(message = "Marked By must be greater than 0")
    private Integer markedBy;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getMarkedBy() {
		return markedBy;
	}

	public void setMarkedBy(Integer markedBy) {
		this.markedBy = markedBy;
	}
    
    
}