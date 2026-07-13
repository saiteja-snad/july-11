package com.example.LMS.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.LMS.Services.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/reports")
@Tag(name = "Reports", description = "Report Management APIs")
@PreAuthorize("hasRole('ADMIN')") // all report endpoints are Admin-only per the design doc
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Operation(
            summary = "Get Attendance Summary Report",
            description = "Returns attendance statistics including present and absent counts"
    )
    @GetMapping("/attendance")
    public ResponseEntity<Object> getAttendanceSummary() {
        return ResponseEntity.ok(
                reportService.getAttendanceSummary());
    }

    @Operation(
            summary = "Get Fee Due Report",
            description = "Returns total fees, total paid amount and pending dues"
    )
    @GetMapping("/fees")
    public ResponseEntity<Object> getFeeDueReport() {
        return ResponseEntity.ok(
                reportService.getFeeDueReport());
    }

    @Operation(
            summary = "Get Student Performance Report",
            description = "Returns overall student performance statistics"
    )
    @GetMapping("/performance")
    public ResponseEntity<Object> getPerformanceReport() {
        return ResponseEntity.ok(
                reportService.getPerformanceReport());
    }
}
