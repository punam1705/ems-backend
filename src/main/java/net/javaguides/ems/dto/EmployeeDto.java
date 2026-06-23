package net.javaguides.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private LocalDate joiningDate;
    private Double salary;

//    public EmployeeDto(Long id, String firstName, String lastName, String email) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.joiningDate=getJoiningDate();
//        this.salary=setSalary();
//    }
}
