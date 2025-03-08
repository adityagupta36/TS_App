package task.TS_App.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;
import task.TS_App.models.Users;
import task.TS_App.repo.UserRepo;
import task.TS_App.service.UsersService;

import java.util.List;

@RestController
@RequestMapping("/admin")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UsersController {

    @Autowired
    UsersService usersService;

    @PostMapping
    ResponseEntity<List<Users>> createAddUser(@Valid @RequestBody List<@Valid Users> users) {
        return new ResponseEntity<>(usersService.createAddUser(users), HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping("/getUser/{id}")
    ResponseEntity<Users> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(usersService.getUser(id));
    }

    @GetMapping("/getAllUsers")
    ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @PutMapping("/updateUser/{id}")
    ResponseEntity<Users> updateUser(@Min(1) @PathVariable Integer id, @Valid @RequestBody Users users) {
        return ResponseEntity.ok(usersService.updateUser(id, users));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@Min(1) @PathVariable Integer id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok("Deleted Success!");
    }


}
