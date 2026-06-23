package net.javaguides.ems.service.impl;

//public class ProjectAssignmentImpl {
//}
//
//package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.entity.ProjectAssignment;
import net.javaguides.ems.repository.ProjectAssignmentRepository;
import net.javaguides.ems.service.ProjectAssignmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectAssignmentServiceImpl
        implements ProjectAssignmentService {

    private ProjectAssignmentRepository repository;

    @Override
    public ProjectAssignment createProject(ProjectAssignment project) {
        return repository.save(project);
    }

    @Override
    public List<ProjectAssignment> getAllProjects() {
        return repository.findAll();
    }

    @Override
    public List<ProjectAssignment> getProjectsByEmployee(String email) {
        return repository.findByEmployeeEmail(email);
    }

    @Override
    public ProjectAssignment updateStatus(Long id, String status) {

        ProjectAssignment project =
                repository.findById(id).orElseThrow();

        project.setStatus(status);

        return repository.save(project);
    }

    @Override
    public void deleteProject(Long id) {

        repository.deleteById(id);

    }
}