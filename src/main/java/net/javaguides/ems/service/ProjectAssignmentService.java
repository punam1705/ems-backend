package net.javaguides.ems.service;

//public class ProjectAssignmentService {
//}
//
//package net.javaguides.ems.service;

import net.javaguides.ems.entity.ProjectAssignment;

import java.util.List;

public interface ProjectAssignmentService {

    ProjectAssignment createProject(ProjectAssignment project);

    List<ProjectAssignment> getAllProjects();

    List<ProjectAssignment> getProjectsByEmployee(String email);

    ProjectAssignment updateStatus(Long id,String status);

    void deleteProject(Long id);

}