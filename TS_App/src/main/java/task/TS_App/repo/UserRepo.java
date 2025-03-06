package task.TS_App.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.TS_App.models.Users;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
}
