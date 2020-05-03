package com.portfolio.portfolio.portfolio.Controller;

import com.portfolio.portfolio.portfolio.Error.ResourceNotFoundException;
import com.portfolio.portfolio.portfolio.Models.Project;
import com.portfolio.portfolio.portfolio.Models.Technology;
import com.portfolio.portfolio.portfolio.Repository.ProjectRepository;
import com.portfolio.portfolio.portfolio.Repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ProjectContoller {

    @Autowired
    private ProjectRepository projectRepository;


    @GetMapping("/projects")
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    @GetMapping("/projects/{projectid}")
    public ResponseEntity<Project> getProjectById(
            @PathVariable(value = "projectid") Long projectID) throws ResourceNotFoundException {
        Project project = projectRepository.findById(projectID)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found on :: "+ projectID));
        return ResponseEntity.ok().body(project);
    }

    @PostMapping("/projects")
    public Project createProject(@Valid @RequestBody Project project) {
        return projectRepository.save(project);
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateProject(
            @PathVariable(value = "id") Long projectID,
            @Valid @RequestBody Project projectDetails) throws ResourceNotFoundException {
        Project project = projectRepository.findById(projectID)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found on :: "+ projectID));

        project.setTitle(projectDetails.getTitle());
        project.setDate(projectDetails.getDate());
        project.setDescription(projectDetails.getDescription());
        project.setImagePathList(projectDetails.getImagePathList());
        project.setFeatureList(projectDetails.getFeatureList());
        project.setGitHubRepositoryPath(projectDetails.getGitHubRepositoryPath());
        project.setTechnologyList(projectDetails.getTechnologyList());

        final Project updatedProject = projectRepository.save(project);
        return ResponseEntity.ok(updatedProject);
    }


    @DeleteMapping("/project/{id}")
    public Map<String, Boolean> deleteProject(
            @PathVariable(value = "id") Long projectID) throws Exception {
        Project project = projectRepository.findById(projectID)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found on :: "+ projectID));

        projectRepository.delete(project);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



}
