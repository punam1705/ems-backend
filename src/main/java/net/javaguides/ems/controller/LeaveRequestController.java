package net.javaguides.ems.controller;

import net.javaguides.ems.entity.LeaveRequest;
import net.javaguides.ems.service.LeaveRequestService;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/leave-request")
public class LeaveRequestController {

    private final LeaveRequestService service;

    public LeaveRequestController(LeaveRequestService service) {
        this.service = service;
    }

    // Employee sends leave request
    @PostMapping
    public LeaveRequest createLeaveRequest(
            @RequestBody LeaveRequest leaveRequest) {

        return service.createLeaveRequest(leaveRequest);
    }

    // Admin sees all requests
    @GetMapping
    public List<LeaveRequest> getAllLeaveRequests() {
        return service.getAllLeaveRequests();
    }

    // Admin approves or rejects
    @PutMapping("/{id}")
    public LeaveRequest updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return service.updateStatus(id, status);
    }


@GetMapping("/employee/{email}")
public List<LeaveRequest> getByEmployeeEmail(
        @PathVariable String email) {

    return service.getByEmployeeEmail(email);
}
}