package org.de.unimuenster.imi.samples.controllers;

import org.de.unimuenster.imi.samples.models.Project;
import org.de.unimuenster.imi.samples.models.Task;
import org.de.unimuenster.imi.samples.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public String indexPage(Model model) {
        List<Project> allProjects = projectService.viewAllProjects();
        model.addAttribute("projects", allProjects);
        return "projects";
    }

    @RequestMapping(path = "/projects/add", method = RequestMethod.GET)
    public  String addProject(Model model){
        Project p1 = new Project();
        model.addAttribute("newProject", p1);
        return "add-project";
    }

    @RequestMapping(path = "/projects", method = RequestMethod.POST)
    public String saveProject(Project project) {
        projectService.saveProject(project);
        return "redirect:/projects";
    }
}