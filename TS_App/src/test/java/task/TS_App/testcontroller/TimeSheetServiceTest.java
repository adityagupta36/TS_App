package task.TS_App.testcontroller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import task.TS_App.models.TimeSheet;
import task.TS_App.models.Users;
import task.TS_App.repo.TimeSheetRepo;
import task.TS_App.repo.UserRepo;
import task.TS_App.service.TimeSheetService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimeSheetServiceTest {

    @Mock
    private TimeSheetRepo timeSheetRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private TimeSheetService timeSheetService;

    private Users user;
    private UserDetails userDetails;
    private TimeSheet timeSheet;

    @BeforeEach
    void setUp() {
        user = new Users();
        user.setUserId(1);
        user.setEmail("test@example.com");
        user.setProject("Project A");

        userDetails = User.withUsername("test@example.com").password("password").authorities("ROLE_EMPLOYEE").build();

        timeSheet = new TimeSheet();
        timeSheet.setTimeSheetId(1);
        timeSheet.setUsers(user);
        timeSheet.setProjectName("Project A");
        timeSheet.setUserEmail("test@example.com");
        timeSheet.setStartTime(LocalDateTime.now());
        timeSheet.setEndTime(LocalDateTime.now().plusHours(8));
    }

    @Test
    void testCreateAddTimeSheet_Success() {
        when(userRepo.findByEmail(userDetails.getUsername())).thenReturn(Optional.of(user));
        when(timeSheetRepo.save(any(TimeSheet.class))).thenReturn(timeSheet);

        TimeSheet savedTimeSheet = timeSheetService.createAddTimeSheet(timeSheet, userDetails);

        assertNotNull(savedTimeSheet);
        assertEquals("test@example.com", savedTimeSheet.getUserEmail());
        verify(timeSheetRepo, times(1)).save(any(TimeSheet.class));
    }

    @Test
    void testCreateAddTimeSheet_UserNotFound() {
        when(userRepo.findByEmail(userDetails.getUsername())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> timeSheetService.createAddTimeSheet(timeSheet, userDetails));
    }

    @Test
    void testGetAllTimeSheetsByEmployee() {
        when(timeSheetRepo.findByUserEmail("test@example.com")).thenReturn(List.of(timeSheet));

        List<TimeSheet> result = timeSheetService.getAllTimeSheetsByEmployee("test@example.com");

        assertEquals(1, result.size());
        assertEquals("test@example.com", result.get(0).getUserEmail());
    }

    @Test
    void testGetParticularTimeSheet_Success() {
        when(timeSheetRepo.findById(1)).thenReturn(Optional.of(timeSheet));

        TimeSheet result = timeSheetService.getParticularTimeSheet(1, "test@example.com");

        assertNotNull(result);
        assertEquals(1, result.getTimeSheetId());
    }

    @Test
    void testGetParticularTimeSheet_AccessDenied() {
        when(timeSheetRepo.findById(1)).thenReturn(Optional.of(timeSheet));

        assertThrows(AccessDeniedException.class, () -> timeSheetService.getParticularTimeSheet(1, "other@example.com"));
    }

    @Test
    void testDeleteTimeSheet_Success() {
        when(timeSheetRepo.findById(1)).thenReturn(Optional.of(timeSheet));
        doNothing().when(timeSheetRepo).deleteById(1);

        timeSheetService.deleteTimeSheet(1, userDetails);

        verify(timeSheetRepo, times(1)).deleteById(1);
    }

    @Test
    void testDeleteTimeSheet_AccessDenied() {
        when(timeSheetRepo.findById(1)).thenReturn(Optional.of(timeSheet));

        assertThrows(AccessDeniedException.class, () -> timeSheetService.deleteTimeSheet(1, User.withUsername("other@example.com").password("password").authorities("ROLE_EMPLOYEE").build()));
    }
}


