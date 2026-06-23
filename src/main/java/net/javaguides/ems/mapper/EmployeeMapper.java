package net.javaguides.ems.mapper;

import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;

public class EmployeeMapper {
//    public static EmployeeDto mapToEmployeeDto(Employee employee){
//        return new EmployeeDto(
//             employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail(),employee.getJoiningDate(),
//                employee.getSalary()
//
//        );
//    }
public static EmployeeDto mapToEmployeeDto(Employee employee){

    EmployeeDto dto = new EmployeeDto();

    dto.setId(employee.getId());
    dto.setFirstName(employee.getFirstName());
    dto.setLastName(employee.getLastName());
    dto.setEmail(employee.getEmail());
    dto.setJoiningDate(employee.getJoiningDate());
    dto.setSalary(employee.getSalary());

    return dto;
}
//    public static Employee mapToEmployee(EmployeeDto employeeDto){
//        return new Employee(
//                employeeDto.getId(),
//                employeeDto.getFirstName(),
//                employeeDto.getLastName(),
//                employeeDto.getEmail(),
//                employeeDto.getJoiningDate(),
//        employeeDto.getSalary()
//
//        );
//    }
public static Employee mapToEmployee(EmployeeDto employeeDto){

    Employee employee = new Employee();

    employee.setId(employeeDto.getId());
    employee.setFirstName(employeeDto.getFirstName());
    employee.setLastName(employeeDto.getLastName());
    employee.setEmail(employeeDto.getEmail());
    employee.setJoiningDate(employeeDto.getJoiningDate());
    employee.setSalary(employeeDto.getSalary());

    return employee;
}
}
