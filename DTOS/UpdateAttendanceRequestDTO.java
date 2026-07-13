package com.example.LMS.Dtos;

import jakarta.validation.constraints.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAttendanceRequestDTO {

    @NotBlank(message = "Status is required")
    @Pattern(
        regexp = "PRESENT|ABSENT|LATE",
        message = "Status must be PRESENT, ABSENT, or LATE"
    )
    private String status;

    @NotNull(message = "Marked By is required")
    @Positive(message = "Marked By must be greater than 0")
    private Integer markedBy;

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