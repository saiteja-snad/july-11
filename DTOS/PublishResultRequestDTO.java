package com.example.LMS.Dtos;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublishResultRequestDTO {

    @NotNull(message = "Exam ID is required")
    @Positive(message = "Exam ID must be greater than 0")
    private Integer examId;

    @NotNull(message = "Student ID is required")
    @Positive(message = "Student ID must be greater than 0")
    private Integer studentId;

    @NotNull(message = "Marks obtained is required")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "Marks obtained cannot be negative")
    private BigDecimal marksObtained;

    @NotBlank(message = "Grade is required")
    @Size(max = 5, message = "Grade cannot exceed 5 characters")
    private String grade;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String remarks;

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public BigDecimal getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(BigDecimal marksObtained) {
		this.marksObtained = marksObtained;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
    
}