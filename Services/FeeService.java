package com.example.LMS.Services;



import java.util.List;

import com.example.LMS.Dtos.CreateFeeRequestDTO;
import com.example.LMS.Dtos.FeeResponseDTO;
import com.example.LMS.Dtos.PayFeeRequestDTO;

public interface FeeService {

	 FeeResponseDTO createFee(CreateFeeRequestDTO dto);

    FeeResponseDTO payFee(Integer feeId,PayFeeRequestDTO dto);

    List<FeeResponseDTO> getStudentFees(Integer studentId);
}