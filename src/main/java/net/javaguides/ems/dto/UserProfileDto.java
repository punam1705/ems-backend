package net.javaguides.ems.dto;

//public class UserProfileDto {
//}
//
//package net.javaguides.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {

    private Long id;
    private String name;
    private String email;
    private LocalDate joiningDate;
    private Double salary;
}