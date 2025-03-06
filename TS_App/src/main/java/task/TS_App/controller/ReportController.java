package task.TS_App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import task.TS_App.models.AdminReport;
import task.TS_App.service.ReportService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin/reports")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/byEmpId/{empId}")
    public ResponseEntity<AdminReport> generateReportByEmpId(
            @PathVariable Integer empId,
            @AuthenticationPrincipal UserDetails adminDetails) {
        AdminReport report = reportService.generateReportBasedOnEmployeeId(empId, adminDetails);
        return ResponseEntity.ok(report);
    }

    @PostMapping("/byEmpIdAndDateRange/{empId}")
    public ResponseEntity<AdminReport> generateReportByEmpIdAndDateRange(
            @PathVariable Integer empId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @AuthenticationPrincipal UserDetails adminDetails) {
        AdminReport report = reportService.generateReportBasedOnEmployeeIdAndDateRange(empId, startDate, endDate, adminDetails);
        return ResponseEntity.ok(report);
    }

    @PostMapping("/byEmpIdAndDate/{empId}")
    public ResponseEntity<AdminReport> generateReportByEmpIdAndDate(
            @PathVariable Integer empId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @AuthenticationPrincipal UserDetails adminDetails) {
        AdminReport report = reportService.generateReportBasedOnEmployeeIdAndParticularDate(empId, date, adminDetails);
        return ResponseEntity.ok(report);
    }
}
