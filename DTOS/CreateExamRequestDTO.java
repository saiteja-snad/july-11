package com.example.LMS.Dtos;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateExamRequestDTO {

    @NotBlank(message = "Exam name is required")
    @Size(max = 100, message = "Exam name cannot exceed 100 characters")
    private String examName;

    @NotNull(message = "Course ID is required")
    @Positive(message = "Course ID must be greater than 0")
    private Integer courseId;

    @NotBlank(message = "Course name is required")
    @Size(max = 100, message = "Course name cannot exceed 100 characters")
    private String courseName;

    @NotNull(message = "Exam date is required")
    private LocalDate examDate;

    @NotNull(message = "Total marks is required")
    @Positive(message = "Total marks must be greater than 0")
    private Integer totalMarks;

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public LocalDate getExamDate() {
		return examDate;
	}

	public void setExamDate(LocalDate examDate) {
		this.examDate = examDate;
	}

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}
    
    
}