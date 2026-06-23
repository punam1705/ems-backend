package net.javaguides.ems.repository;

//public class ProjectAssignmentRepository {
//}
//
//package net.javaguides.ems.repository;

import net.javaguides.ems.entity.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectAssignmentRepository
        extends JpaRepository<ProjectAssignment,Long> {

    List<ProjectAssignment> findByEmployeeEmail(String employeeEmail);

}