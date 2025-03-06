package task.TS_App.testcontroller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import task.TS_App.models.AdminReport;
import task.TS_App.models.TimeSheet;
import task.TS_App.models.Users;
import task.TS_App.repo.AdminRepo;
import task.TS_App.repo.TimeSheetRepo;
import task.TS_App.repo.UserRepo;
import task.TS_App.service.ReportService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private AdminRepo adminRepo;

    @Mock
    private TimeSheetRepo timeSheetRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private ReportService reportService;

    private Users adminUser;
    private Users employee;
    private UserDetails adminDetails;
    private UserDetails nonAdminDetails;
    private TimeSheet timeSheet;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @BeforeEach
    void setUp() {
        adminUser = new Users();
        adminUser.setUserId(1);
        adminUser.setEmail("admin@example.com");

        employee = new Users();
        employee.setUserId(2);
        employee.setEmail("employee@example.com");

        adminDetails = new User("admin@example.com", "password", List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        nonAdminDetails = new User("employee@example.com", "password", List.of(new SimpleGrantedAuthority("ROLE_EMPLOYEE")));

        timeSheet = new TimeSheet();
        timeSheet.setTimeSheetId(1);
        timeSheet.setUsers(employee);
        timeSheet.setProjectName("Project A");
        timeSheet.setTotalHours(8.0);
        timeSheet.setStartTime(LocalDateTime.now());
        timeSheet.setEndTime(LocalDateTime.now().plusHours(8));

        startDate = LocalDateTime.now().minusDays(7);
        endDate = LocalDateTime.now();
    }

    @Test
    void testGenerateReportByEmployeeId_Success() {
        when(userRepo.findAll()).thenReturn(List.of(employee));
        when(timeSheetRepo.findByUsersUserId(anyInt())).thenReturn(List.of(timeSheet));
        when(adminRepo.save(any(AdminReport.class))).thenReturn(new AdminReport());

        AdminReport report = reportService.generateReportBasedOnEmployeeId(2, adminDetails);

        assertNotNull(report);
        verify(adminRepo, times(1)).save(any(AdminReport.class));
    }

    @Test
    void testGenerateReportByEmployeeId_AccessDenied() {
        assertThrows(AccessDeniedException.class, () -> reportService.generateReportBasedOnEmployeeId(2, nonAdminDetails));
    }

    @Test
    void testGenerateReportByEmployeeIdAndDateRange_Success() {
        when(userRepo.findAll()).thenReturn(List.of(employee));
        when(timeSheetRepo.findByEmployeeIdAndDateRange(anyInt(), any(), any())).thenReturn(List.of(timeSheet));
        when(adminRepo.save(any(AdminReport.class))).thenReturn(new AdminReport());

        AdminReport report = reportService.generateReportBasedOnEmployeeIdAndDateRange(2, startDate, endDate, adminDetails);

        assertNotNull(report);
        verify(adminRepo, times(1)).save(any(AdminReport.class));
    }

    @Test
    void testGenerateReportByEmployeeIdAndDateRange_AccessDenied() {
        assertThrows(AccessDeniedException.class, () -> reportService.generateReportBasedOnEmployeeIdAndDateRange(2, startDate, endDate, nonAdminDetails));
    }

    @Test
    void testGenerateReportByEmployeeIdAndParticularDate_Success() {
        when(userRepo.findAll()).thenReturn(List.of(employee));
        when(timeSheetRepo.findByEmployeeIdAndDate(anyInt(), any())).thenReturn(List.of(timeSheet));
        when(adminRepo.save(any(AdminReport.class))).thenReturn(new AdminReport());

        AdminReport report = reportService.generateReportBasedOnEmployeeIdAndParticularDate(2, startDate, adminDetails);

        assertNotNull(report);
        verify(adminRepo, times(1)).save(any(AdminReport.class));
    }
}
