package task.TS_App.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportId;

    private String generatedBy;

    private LocalDateTime createdAt;

    private Double totalWorkingHours;

    private Double averageWorkingHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users users;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @OneToMany(mappedBy = "adminReport", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReportDetail> reportDetails;

}
