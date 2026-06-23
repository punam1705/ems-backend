package net.javaguides.ems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "employees")

public class Employee {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

        @Column(name ="first_name")
    private String firstName;

    @Column(name ="last_name")
    private String lastName;

    @Column(name ="email_id",nullable = false,unique = true)
    private String email;

    @Column(name = "joining_date")
    private LocalDate joiningDate;

    @Column(name = "salary")
    private Double salary;

    @OneToOne
    @JoinColumn(
            name = "email_id",
            referencedColumnName = "email",
            insertable = false,
            updatable = false
    )
    private User user;



}
