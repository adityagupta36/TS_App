package task.TS_App.testcontroller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import task.TS_App.models.Users;
import task.TS_App.repo.UserRepo;
import task.TS_App.service.UsersService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito in JUnit 5
class UsersServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UsersService usersService;

    private Users user;

    @BeforeEach
    void setUp() {
        user = new Users();
        user.setUserId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
    }

    @Test
    void testCreateAddUser() {
        when(passwordEncoder.encode(anyString())).thenReturn("hashed_password");
        when(userRepo.save(any(Users.class))).thenReturn(user);

        List<Users> usersList = List.of(user);
        List<Users> createdUsers = usersService.createAddUser(usersList);

        assertEquals(1, createdUsers.size());
        assertEquals("hashed_password", createdUsers.get(0).getPassword());
        verify(userRepo, times(1)).save(any(Users.class));
    }

    @Test
    void testGetUser_Success() {
        when(userRepo.findById(1)).thenReturn(Optional.of(user));

        Users foundUser = usersService.getUser(1);

        assertNotNull(foundUser);
        assertEquals("John", foundUser.getFirstName());
    }

    @Test
    void testGetUser_NotFound() {
        when(userRepo.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> usersService.getUser(2));
    }

    @Test
    void testUpdateUser() {
        Users updatedUser = new Users();
        updatedUser.setFirstName("Jane");
        updatedUser.setLastName("Doe");
        updatedUser.setEmail("jane.doe@example.com");
        updatedUser.setPassword("newPassword");

        when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepo.save(any(Users.class))).thenReturn(updatedUser);
        when(passwordEncoder.encode(anyString())).thenReturn("hashedNewPassword");

        Users result = usersService.updateUser(1, updatedUser);

        assertEquals("Jane", result.getFirstName());
        assertEquals("hashedNewPassword", result.getPassword());
    }

    @Test
    void testDeleteUser_Success() {
        when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
        doNothing().when(userRepo).deleteById(anyInt());

        usersService.deleteUser(1);

        verify(userRepo, times(1)).deleteById(1);
    }
}
