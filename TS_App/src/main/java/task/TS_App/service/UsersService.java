package task.TS_App.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import task.TS_App.models.Users;
import task.TS_App.repo.UserRepo;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsersService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public List<Users> createAddUser(List<Users> users) {
        for (Users users1 : users) {
            users1.setPassword(passwordEncoder.encode(users1.getPassword()));
            userRepo.save(users1);
        }
        return users;

    }

    @Transactional
    public Users getUser(Integer id) {
        return userRepo.findById(id).orElseThrow(() -> new NoSuchElementException("User Not Found!"));
    }

    public List<Users> getAllUsers() {
        System.out.println();
        return userRepo.findAll();
    }

    public Users updateUser(Integer id, Users users) {
        Users user = userRepo.findById(id).orElseThrow(() -> new NoSuchElementException("User Not Found!"));
        user.setFirstName(users.getFirstName());
        user.setLastName(users.getLastName());
        user.setEmail(users.getEmail());
        user.setEmployeeId(users.getEmployeeId());
        user.setProject(users.getProject());
        user.setRole(users.getRole());
        user.setIsActive(users.getIsActive());
        user.setCreatedDate(users.getCreatedDate());
        user.setUpdatedDate(users.getUpdatedDate());
        users.setPassword(passwordEncoder.encode(users.getPassword()));

        return userRepo.save(user);
    }

    public void deleteUser(Integer id) {
        Users user = userRepo.findById(id).orElseThrow(() -> new NoSuchElementException("User Not Found!"));
        userRepo.deleteById(id);
    }


}
