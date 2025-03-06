package task.TS_App.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.TS_App.exception.NoReportsFoundException;
import task.TS_App.models.AdminReport;
import task.TS_App.models.ReportDetail;
import task.TS_App.models.TimeSheet;
import task.TS_App.models.Users;
import task.TS_App.repo.AdminRepo;
import task.TS_App.repo.TimeSheetRepo;
import task.TS_App.repo.UserRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    private AdminRepo adminReportRepo;

    @Autowired
    private TimeSheetRepo timeSheetRepo;

    @Autowired
    private UserRepo userRepo;

    /*3 filters must and 2 Additional(not mandatory):
    1. empId -> [admin will get all Timesheet wrt that id]
    2. empId + Date Range
    3. empId + Particular Date

    4. get Report for all Employees for particular Date Range
    5. get Report for All Employees for particular Date
    */

    @Transactional
    public AdminReport generateReportBasedOnEmployeeId(Integer empId, UserDetails userDetails) {
        verifyAdmin(userDetails);
        Users users = getUserByEmployeeId(empId);  //unique user By Particular ID-> mil gaya
        List<TimeSheet> timeSheets = timeSheetRepo.findByUsersUserId(empId);
        return saveReport(timeSheets, users, userDetails.getUsername(), null, null);
    }

    @Transactional
    public AdminReport generateReportBasedOnEmployeeIdAndDateRange(Integer empId, LocalDateTime startDate, LocalDateTime endDate, UserDetails userDetails) {
        verifyAdmin(userDetails);
        Users users = getUserByEmployeeId(empId);
        List<TimeSheet> timeSheets = timeSheetRepo.findByEmployeeIdAndDateRange(empId, startDate, endDate);
        return saveReport(timeSheets, users, userDetails.getUsername(), startDate, endDate);
    }

    @Transactional
    public AdminReport generateReportBasedOnEmployeeIdAndParticularDate(Integer empId, LocalDateTime particularDate, UserDetails userDetails) {
        verifyAdmin(userDetails);
        Users users = getUserByEmployeeId(empId);
        List<TimeSheet> timeSheets = timeSheetRepo.findByEmployeeIdAndDate(empId, particularDate);
        return saveReport(timeSheets, users, userDetails.getUsername(), particularDate, particularDate);
    }


    //this logic was being used again and again, so created a function of it
    @Transactional
    private AdminReport saveReport(List<TimeSheet> timeSheets, Users users, String generatedBy, LocalDateTime startDate, LocalDateTime endDate) {
        AdminReport adminReport = new AdminReport();
        adminReport.setGeneratedBy(generatedBy);
        adminReport.setCreatedAt(LocalDateTime.now());
        adminReport.setUsers(users);
        adminReport.setStartDate(startDate);
        adminReport.setEndDate(endDate);

//        Double totalWorkingHours = timeSheets.stream().collect(Collectors.summingDouble((value)-> value.getTotalHours());
        Double totalWorkingHours = timeSheets.stream().mapToDouble((map) -> map.getTotalHours()).sum();
        Double avgHours = timeSheets.isEmpty() ? 0 : totalWorkingHours / timeSheets.size();   //ternary operator
        adminReport.setAverageWorkingHours(avgHours);
        adminReport.setTotalWorkingHours(totalWorkingHours);

        List<ReportDetail> reportDetails = timeSheets.stream().map(
                (ts) -> {
                    ReportDetail reportDetail = new ReportDetail();
                    reportDetail.setAdminReport(adminReport);
                    reportDetail.setWorkDetail(ts.getWorkDetail());
                    reportDetail.setDate(ts.getDate());
                    reportDetail.setTotalHours(ts.getTotalHours());
                    reportDetail.setProjectName(ts.getProjectName());
                    return reportDetail;
                }
        ).collect(Collectors.toList());
        if (reportDetails.size()>0){
            adminReport.setReportDetails(reportDetails);
        }
        else throw new NoReportsFoundException("No Such Reports");
//        adminReport.setReportDetails(reportDetails);

        return adminReportRepo.save(adminReport);
    }


    public Users getUserByEmployeeId(Integer empId) {
        return userRepo.findAll().stream().filter((a) -> a.getUserId().equals(empId)).findFirst().orElseThrow(() -> new RuntimeException("No Such Employee with Employee Id: " + empId));
    }


    private void verifyAdmin(UserDetails adminDetails) {
        if (!adminDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new AccessDeniedException("Only admins can generate reports");
        }
    }


}
