package task.TS_App.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer timeSheetId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    private String userEmail;

    private String projectName;

    @NotNull(message = "Date cannot be null")
    private LocalDateTime date;

    @NotNull(message = "Start time cannot be null")
    private LocalDateTime startTime;

    @NotNull(message = "End time cannot be null")
    private LocalDateTime endTime;

    @NotNull(message = "Total hours cannot be null")
    @DecimalMin(value = "0.5", message = "Total hours must be at least 0.5")
    @DecimalMax(value = "24.0", message = "Total hours must be at most 24")
    private Double totalHours;

    @Lob
    @NotBlank(message = "Work detail cannot be empty")
    @Size(max = 500, message = "Work detail must not exceed 500 characters")
    private String workDetail;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

