package net.javaguides.ems.service;

import net.javaguides.ems.entity.LeaveRequest;

import java.util.List;

public interface LeaveRequestService {

    LeaveRequest createLeaveRequest(LeaveRequest leaveRequest);

    List<LeaveRequest> getAllLeaveRequests();

    LeaveRequest updateStatus(Long id, String status);

    List<LeaveRequest> getByEmployeeEmail(String email);
}
