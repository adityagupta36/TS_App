package task.TS_App.testcontroller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import task.TS_App.controller.UsersController;
import task.TS_App.models.App_Role;
import task.TS_App.models.Role;
import task.TS_App.models.Users;
import task.TS_App.service.UsersService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsersService usersService;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
    }

    private Users getUser() {
        Role role = new Role();
        role.setRoleId(1);
        role.setAppRole(App_Role.ROLE_ADMIN);

        Users user = new Users();
        user.setUserId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setEmployeeId("E12345");
        user.setProject("Project Alpha");
        user.setIsActive(true);
        user.setPassword("hashedPassword");
        user.setRole(role);

        return user;
    }


    @Test
    void testCreateAddUser() throws Exception {
        when(usersService.createAddUser(any())).thenReturn(List.of(getUser()));

        String requestBody = """
                [{
                    "userId": 1,
                    "firstName": "John",
                    "lastName": "Doe",
                    "email": "john.doe@example.com",
                    "employeeId": "E12345",
                    "project": "Project Alpha",
                    "isActive": true,
                    "password": "hashedPassword",
                    "role": { "roleId": 1 }
                }]
                """;

        mockMvc.perform(post("/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"));


    }

    @Test
    void testGetUser() throws Exception {
        when(usersService.getUser(1)).thenReturn(getUser());

        mockMvc.perform(get("/admin/getUser/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(usersService.getAllUsers()).thenReturn(List.of(getUser()));

        mockMvc.perform(get("/admin/getAllUsers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void testUpdateUser() throws Exception {
        Users updatedUser = getUser();
        updatedUser.setFirstName("Jane");
        updatedUser.setEmail("jane.doe@example.com");

        when(usersService.updateUser(anyInt(), any())).thenReturn(updatedUser);

        String requestBody = """
                {
                    "userId": 1,
                    "firstName": "Jane",
                    "lastName": "Doe",
                    "email": "jane.doe@example.com",
                    "employeeId": "E12345",
                    "project": "Project Alpha",
                    "isActive": true,
                    "password": "hashedPassword",
                    "role": { "roleId": 1 }
                }
                """;

        mockMvc.perform(put("/admin/updateUser/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(usersService).deleteUser(1);

        mockMvc.perform(delete("/admin/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Success!"));
    }
}

