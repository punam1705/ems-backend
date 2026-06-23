package net.javaguides.ems.repository;

//public class LeaveRequestRepository {

import net.javaguides.ems.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeEmail(String employeeEmail);
}
