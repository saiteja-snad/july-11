package com.example.LMS.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LMS.Dtos.CreateFeeRequestDTO;
import com.example.LMS.Dtos.FeeResponseDTO;
import com.example.LMS.Dtos.PayFeeRequestDTO;
import com.example.LMS.Services.FeeService;

@RestController
@RequestMapping("/api/fees")
public class FeeController {

    @Autowired
    private FeeService feeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FeeResponseDTO> createFee(
            @RequestBody CreateFeeRequestDTO dto) {
        return ResponseEntity.ok(
                feeService.createFee(dto));
    }

    // NOTE: doc lists "Admin/Cashier" — CASHIER is not currently in the
    // roles table from the design doc (only admin/staff/teacher/student).
    // Confirm whether CASHIER should be a real distinct role before seeding
    // the roles table; adjust this annotation to match once decided.
    @PutMapping("/{feeId}/pay")
    @PreAuthorize("hasAnyRole('ADMIN','CASHIER')")
    public ResponseEntity<FeeResponseDTO> payFee(
            @PathVariable Integer feeId,
            @RequestBody PayFeeRequestDTO dto) {
        return ResponseEntity.ok(
                feeService.payFee(feeId, dto));
    }

    // NOTE: "Student/Admin" — enforce ownership (studentId belongs to caller)
    // inside feeService.getStudentFees() for the STUDENT role.
    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('STUDENT','ADMIN')")
    public ResponseEntity<List<FeeResponseDTO>> getStudentFees(
            @PathVariable Integer studentId) {
        return ResponseEntity.ok(
                feeService.getStudentFees(studentId));
    }
}
