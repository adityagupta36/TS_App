package task.TS_App.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotEmpty(message = "First Name is required")
    @Size(min = 2,max = 25 , message = "First name must be between 2 and 25 characters")
    private String firstName;

    @NotEmpty(message = "Last Name is required")
    @Size(min = 2,max = 25 , message = "Last name must be between 2 and 25 characters")
    private String lastName;

    private String employeeId;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String  email;

    @NotBlank(message = "Project name is required")
    @Size(max = 100, message = "Project name must be at most 100 characters")
    private String Project;

    @NotNull(message = "Active status cannot be null")
    private Boolean isActive;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JsonBackReference
    @ToString.Exclude
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    @NotNull(message = "Role is required")
    private Role role;

    //    @JsonIgnore
    @NotBlank(message = "Password is Required")
//    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Password must contain only alphabets and numbers (no special characters)")
    String password;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.appRole.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
