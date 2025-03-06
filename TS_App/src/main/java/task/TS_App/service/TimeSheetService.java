package task.TS_App.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import task.TS_App.models.TimeSheet;
import task.TS_App.models.Users;
import task.TS_App.repo.TimeSheetRepo;
import task.TS_App.repo.UserRepo;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TimeSheetService {
    @Autowired
    TimeSheetRepo timeSheetRepo;
    @Autowired
    UserRepo userRepo;

    public TimeSheet createAddTimeSheet(TimeSheet timeSheet, UserDetails userDetails) {
        Users loggedInUser = userRepo.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        timeSheet.setUsers(loggedInUser);
        timeSheet.setProjectName(loggedInUser.getProject());
        timeSheet.setUserEmail(loggedInUser.getEmail());

        if (!timeSheet.getUsers().getEmail().equals(userDetails.getUsername())) {
            throw new AccessDeniedException("You are not authorized to create this timesheet.");
        }
        return timeSheetRepo.save(timeSheet);
    }

    public List<TimeSheet> getAllTimeSheetsByEmployee(String username) {
        return timeSheetRepo.findByUserEmail(username);
    }

    public TimeSheet getParticularTimeSheet(Integer id, String username) {
        TimeSheet sheet = timeSheetRepo.findById(id).orElseThrow(() -> new NoSuchElementException("No Such Element Found!!"));
        if (!sheet.getUserEmail().equals(username)) {
            throw new AccessDeniedException("Not Authorized to see Another User TImeSheet!");
        }
        return sheet;
    } 

    public TimeSheet updateTimeSheet(Integer id, TimeSheet timeSheet, String userName) {
        Users loggedInUser = userRepo.findByEmail(userName)
                .orElseThrow(() -> new NoSuchElementException("User not found"));  // CHECKING:: currently logged in user is present in DB or Not??

        TimeSheet timeSheet1 = timeSheetRepo.findById(id).orElseThrow(() -> new NoSuchElementException("time sheet id not found! -> " + id));
        if (!timeSheet1.getUserEmail().equals(userName)) {
            throw new AccessDeniedException("You are not authorized to update this timesheet.");
        }
        if(timeSheet1.getCreatedAt().toLocalDate().equals(LocalDate.now())){
            timeSheet1.setUsers(loggedInUser);
            timeSheet1.setProjectName(loggedInUser.getProject());
            timeSheet1.setDate(timeSheet.getDate());
            timeSheet1.setStartTime(timeSheet.getStartTime());
            timeSheet1.setEndTime(timeSheet.getEndTime());
            timeSheet1.setTotalHours(timeSheet.getTotalHours());
            timeSheet1.setWorkDetail(timeSheet.getWorkDetail());
            timeSheet1.setUserEmail(loggedInUser.getEmail());
        }
        else throw new AccessDeniedException("You Cannot Update Timesheet other than Today!");
        return timeSheetRepo.save(timeSheet1);
    }


    public void deleteTimeSheet(Integer id, UserDetails userDetails) {
        TimeSheet timeSheet = timeSheetRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Time sheet ID not found: " + id));

        if (!timeSheet.getUserEmail().equals(userDetails.getUsername())) {
            throw new AccessDeniedException("You are not authorized to delete this timesheet.");
        }
        timeSheetRepo.deleteById(id);
    }


}
