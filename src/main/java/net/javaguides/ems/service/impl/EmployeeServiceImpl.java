package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.entity.User;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.repository.UserRepository;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private UserRepository userRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee= employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee= employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id: "+ employeeId));

//        return EmployeeMapper.mapToEmployeeDto(employee);
        System.out.println("Entity JoiningDate = " + employee.getJoiningDate());
        System.out.println("Entity Salary = " + employee.getSalary());

        EmployeeDto dto = EmployeeMapper.mapToEmployeeDto(employee);

        System.out.println("DTO JoiningDate = " + dto.getJoiningDate());
        System.out.println("DTO Salary = " + dto.getSalary());

        return dto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());

    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not exist with given id :"+ employeeId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setJoiningDate(updatedEmployee.getJoiningDate());
        employee.setSalary(updatedEmployee.getSalary());
        Employee updatedEmployeeObj =  employeeRepository.save(employee);

//        User user = updatedEmployeeObj.getUser();
        User user = userRepository.findByEmail(employee.getEmail())
                .orElse(null);

        if (user != null) {

            user.setName(
                    updatedEmployeeObj.getFirstName()
                            + " "
                            + updatedEmployeeObj.getLastName());

            user.setEmail(updatedEmployeeObj.getEmail());

            userRepository.save(user);
        }

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not exist with given id :"+ employeeId));
        employeeRepository.deleteById(employeeId);
    }
}
