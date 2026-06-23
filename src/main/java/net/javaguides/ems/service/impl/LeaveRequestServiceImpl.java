package net.javaguides.ems.service.impl;

//public class LeaveRequestServiceImpl {
//}

//package com.example.ems.service.impl;

//import com.example.ems.entity.LeaveRequest;
//import com.example.ems.entity.LeaveStatus;
//import com.example.ems.repository.LeaveRequestRepository;
//import com.example.ems.service.LeaveRequestService;
//import org.springframework.stereotype.Service;

import net.javaguides.ems.dto.LeaveStatus;
import net.javaguides.ems.entity.LeaveRequest;
import net.javaguides.ems.repository.LeaveRequestRepository;
import net.javaguides.ems.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository repository;

    public LeaveRequestServiceImpl(LeaveRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {

        leaveRequest.setStatus(LeaveStatus.PENDING);

        return repository.save(leaveRequest);
    }

    @Override
    public List<LeaveRequest> getAllLeaveRequests() {
        return repository.findAll();
    }

    @Override
    public LeaveRequest updateStatus(Long id, String status) {

        LeaveRequest leaveRequest =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Leave Request not found"));

        leaveRequest.setStatus(LeaveStatus.valueOf(status));

        return repository.save(leaveRequest);
    }

//    @Autowired
//    private LeaveRequestRepository repository;
//
    @Override
    public List<LeaveRequest> getByEmployeeEmail(String email) {
        return repository.findByEmployeeEmail(email);
    }
}
