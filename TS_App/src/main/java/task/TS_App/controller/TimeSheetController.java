package task.TS_App.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import task.TS_App.models.TimeSheet;
import task.TS_App.models.Users;
import task.TS_App.service.TimeSheetService;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/timesheet")
public class TimeSheetController {
    @Autowired
    TimeSheetService timeSheetService;


    //currently authenticated employee will create timesheet
    @PostMapping
    ResponseEntity<TimeSheet> createAddTimeSheet(@Valid @RequestBody TimeSheet timeSheet, @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(timeSheetService.createAddTimeSheet(timeSheet, userDetails), HttpStatus.CREATED);
    }

    //currently authenticated employee will get all timesheet
    @GetMapping("/getAllTimeSheet")
    public ResponseEntity<List<TimeSheet>> getAllTimeSheetsByEmployee(@AuthenticationPrincipal UserDetails userDetails) {
        List<TimeSheet> timesheets = timeSheetService.getAllTimeSheetsByEmployee(userDetails.getUsername());
        return ResponseEntity.ok(timesheets);
    }

    @GetMapping("/getParticularTimeSheet/{id}")
    public ResponseEntity<TimeSheet> getParticularTimeSheet(@PathVariable Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(timeSheetService.getParticularTimeSheet(id, userDetails.getUsername()));
    }

    @PutMapping("updateTimeSheet/{id}")
    ResponseEntity<TimeSheet> updateTimeSheet(@PathVariable Integer id, @Valid @RequestBody TimeSheet timeSheet, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(timeSheetService.updateTimeSheet(id, timeSheet, userDetails.getUsername()));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteTimeSheet(@PathVariable Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        timeSheetService.deleteTimeSheet(id, userDetails);
        return ResponseEntity.ok("Deleted Success!");
    }

}
