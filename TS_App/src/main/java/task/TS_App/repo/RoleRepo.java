package task.TS_App.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.TS_App.models.App_Role;
import task.TS_App.models.Role;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByAppRole(App_Role appRole);


}
