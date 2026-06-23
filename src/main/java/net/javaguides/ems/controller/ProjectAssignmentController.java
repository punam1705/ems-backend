package net.javaguides.ems.controller;


import lombok.AllArgsConstructor;
import net.javaguides.ems.entity.ProjectAssignment;
import net.javaguides.ems.service.ProjectAssignmentService;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/projects")
@AllArgsConstructor
@CrossOrigin("*")
public class ProjectAssignmentController {

    private ProjectAssignmentService service;

    // assign project
    @PostMapping
    public ProjectAssignment createProject(
            @RequestBody ProjectAssignment project){

        project.setStatus("IN_PROGRESS");

        return service.createProject(project);
    }

    // all projects
    @GetMapping
    public List<ProjectAssignment> getAllProjects(){

        return service.getAllProjects();
    }

    // employee projects
    @GetMapping("/employee/{email}")
    public List<ProjectAssignment> getProjectsByEmployee(
            @PathVariable String email){

        return service.getProjectsByEmployee(email);
    }

    // update status
    @PutMapping("/{id}")
    public ProjectAssignment updateStatus(
            @PathVariable Long id,
            @RequestParam String status){

        return service.updateStatus(id,status);
    }

    // delete
    @DeleteMapping("/{id}")
    public void deleteProject(
            @PathVariable Long id){

        service.deleteProject(id);
    }
}