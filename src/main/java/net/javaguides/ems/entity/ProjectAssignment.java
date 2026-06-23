package net.javaguides.ems.entity;

//public class ProjectAssignment {
//}

//package net.javaguides.ems.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class ProjectAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeName;

    private String employeeEmail;

    private String department;

    private String projectName;

    private String roleAssigned;

    private LocalDate startDate;

    private LocalDate completionDate;

    private String status;
}