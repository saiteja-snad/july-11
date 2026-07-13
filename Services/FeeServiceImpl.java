package com.example.LMS.Services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.Dtos.CreateFeeRequestDTO;
import com.example.LMS.Dtos.FeeResponseDTO;
import com.example.LMS.Dtos.PayFeeRequestDTO;
import com.example.LMS.Entity.Fee;
import com.example.LMS.Entity.Student;
import com.example.LMS.Repositorys.FeeRepository;
import com.example.LMS.Repositorys.StudentRepository;

@Service
public class FeeServiceImpl implements FeeService {

    @Autowired
    private FeeRepository feeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public FeeResponseDTO createFee(
            CreateFeeRequestDTO dto) {

        Student student = studentRepository
                .findById(dto.getStudentId())
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        Fee fee = new Fee();

        fee.setStudent(student);
        fee.setFeeType(dto.getFeeType());
        fee.setAmount(dto.getAmount());
        fee.setDueDate(dto.getDueDate());
        fee.setPaidAmount(BigDecimal.ZERO);
        fee.setStatus("PENDING");
        fee.setCreatedAt(LocalDateTime.now());

        Fee savedFee = feeRepository.save(fee);

        return convertToDto(savedFee);
    }

    @Override
    public FeeResponseDTO payFee(
            Integer feeId,
            PayFeeRequestDTO dto) {

        Fee fee = feeRepository.findById(feeId)
                .orElseThrow(() ->
                        new RuntimeException("Fee not found"));

        BigDecimal currentPaid =
                fee.getPaidAmount() == null
                        ? BigDecimal.ZERO
                        : fee.getPaidAmount();

        BigDecimal updatedPaid =
                currentPaid.add(dto.getPaymentAmount());

        fee.setPaidAmount(updatedPaid);

        if (updatedPaid.compareTo(fee.getAmount()) >= 0) {
            fee.setStatus("PAID");
        } else {
            fee.setStatus("PARTIAL");
        }

        Fee updatedFee = feeRepository.save(fee);

        return convertToDto(updatedFee);
    }

    @Override
    public List<FeeResponseDTO> getStudentFees(
            Integer studentId) {

        return feeRepository
                .findByStudentStudentId(studentId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private FeeResponseDTO convertToDto(Fee fee) {

        FeeResponseDTO dto = new FeeResponseDTO();

        dto.setFeeId(fee.getFeeId());

        dto.setStudentId(
                fee.getStudent().getStudentId());

        dto.setStudentName(
                fee.getStudent().getFirstName()
                        + " "
                        + fee.getStudent().getLastName());

        dto.setFeeType(fee.getFeeType());

        dto.setAmount(fee.getAmount());

        dto.setPaidAmount(fee.getPaidAmount());

        dto.setDueDate(fee.getDueDate());

        dto.setStatus(fee.getStatus());

        return dto;
    }
}