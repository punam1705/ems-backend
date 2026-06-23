package net.javaguides.ems.entity;

//public class LeaveRequest {
//}

//package com.example.ems.entity;

import jakarta.persistence.*;
import lombok.Data;
import net.javaguides.ems.dto.LeaveStatus;

@Entity
@Table(name = "leave_requests")
@Data
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeName;

    private String employeeEmail;

    private String fromDate;

    private String toDate;

    @Column(length = 1000)
    private String reason;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}