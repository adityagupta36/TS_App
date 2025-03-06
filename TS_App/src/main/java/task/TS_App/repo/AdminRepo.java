package task.TS_App.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import task.TS_App.models.AdminReport;

public interface AdminRepo extends JpaRepository<AdminReport, Integer> {
}
