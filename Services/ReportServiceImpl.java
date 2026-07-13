package com.example.LMS.Services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.Entity.Attendance;
import com.example.LMS.Entity.Fee;
import com.example.LMS.Entity.Result;
import com.example.LMS.Repositorys.AttendanceRepository;
import com.example.LMS.Repositorys.FeeRepository;
import com.example.LMS.Repositorys.ResultRepository;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private FeeRepository feeRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Object getAttendanceSummary() {

        List<Attendance> attendances =
                attendanceRepository.findAll();

        long total = attendances.size();

        long presentCount = attendances.stream()
                .filter(a -> "PRESENT".equalsIgnoreCase(a.getStatus()))
                .count();

        long absentCount = attendances.stream()
                .filter(a -> "ABSENT".equalsIgnoreCase(a.getStatus()))
                .count();

        Map<String, Object> report = new HashMap<>();

        report.put("totalAttendanceRecords", total);
        report.put("presentCount", presentCount);
        report.put("absentCount", absentCount);

        return report;
    }

    @Override
    public Object getFeeDueReport() {

        List<Fee> fees = feeRepository.findAll();

        BigDecimal totalFee = BigDecimal.ZERO;
        BigDecimal totalPaid = BigDecimal.ZERO;

        for (Fee fee : fees) {

            if (fee.getAmount() != null) {
                totalFee = totalFee.add(fee.getAmount());
            }

            if (fee.getPaidAmount() != null) {
                totalPaid = totalPaid.add(fee.getPaidAmount());
            }
        }

        BigDecimal totalDue = totalFee.subtract(totalPaid);

        Map<String, Object> report = new HashMap<>();

        report.put("totalFee", totalFee);
        report.put("totalPaid", totalPaid);
        report.put("totalDue", totalDue);

        return report;
    }

    @Override
    public Object getPerformanceReport() {

        List<Result> results = resultRepository.findAll();

        double averageMarks = results.stream()
                .mapToDouble(r ->
                        r.getMarksObtained() != null
                                ? r.getMarksObtained().doubleValue()
                                : 0.0)
                .average()
                .orElse(0.0);

        Map<String, Object> report = new HashMap<>();

        report.put("totalResults", results.size());
        report.put("averageMarks", averageMarks);

        return report;
    }
}