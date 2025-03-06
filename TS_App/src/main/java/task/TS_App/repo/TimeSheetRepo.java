package task.TS_App.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import task.TS_App.models.TimeSheet;
import task.TS_App.models.Users;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimeSheetRepo extends JpaRepository<TimeSheet, Integer> {
    List<TimeSheet> findByUserEmail(String userEmail);

    @Query(value = "SELECT * FROM timesheet_app.time_sheet WHERE user_id = :empId AND date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<TimeSheet> findByEmployeeIdAndDateRange(Integer empId, LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "SELECT * FROM timesheet_app.time_sheet WHERE user_id = :empId AND date BETWEEN :particularDate AND TIMESTAMP(:particularDate, '23:59:59')", nativeQuery = true)
    List<TimeSheet> findByEmployeeIdAndDate(Integer empId, LocalDateTime particularDate);

    List<TimeSheet> findByUsersUserId(Integer empId);

}
