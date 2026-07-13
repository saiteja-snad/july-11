package com.example.LMS.Dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEnrollmentRequestDTO {

    @NotNull(message = "Student ID is required")
    @Positive(message = "Student ID must be greater than 0")
    private Integer studentId;

    @NotNull(message = "Course ID is required")
    @Positive(message = "Course ID must be greater than 0")
    private Integer courseId;

    @NotBlank(message = "Academic year is required")
    @Size(max = 20, message = "Academic year cannot exceed 20 characters")
    private String academicYear;

    @NotBlank(message = "Semester is required")
    @Size(max = 20, message = "Semester cannot exceed 20 characters")
    private String semester;

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

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}
    
    
}