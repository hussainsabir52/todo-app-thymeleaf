package org.de.unimuenster.imi.samples.services;

import org.de.unimuenster.imi.samples.models.Project;
import org.de.unimuenster.imi.samples.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project saveProject(Project project){
        Project project1 = projectRepository.save(project);
        try{
            project1.setName(project.getName());
            project1.setDescription(project.getDescription());
            project1.setStartDate(project.getStartDate());
            project1.setEndDate(project.getEndDate());
            Date time = new Date();
            long newTime = time.getTime();
            Timestamp completedTime = new Timestamp(newTime);
            project1.setCreatedAt(completedTime);

            projectRepository.save(project1);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return project1;
    }


    @Override
    public Project viewProject(Integer id) {
        Project project = null;
        try {
            project = projectRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return project;
    }

    @Override
    public Project findProject(Integer id) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null) {
            return projectRepository.save(project);
        }
        return null;
    }

    @Override
    public List<Project> viewAllProjects() {
        List<Project> p = null;
        try {
            p = projectRepository.findAll();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return p;
    }


    @Override
    public Project updateProject(Project project) {
        Project p1 = null;
        try{
            p1 = projectRepository.findById(project.getId()).orElse(null);
            if(p1!=null){
                p1.setName(project.getName());
                p1.setDescription(project.getDescription());
                p1.setStartDate(project.getStartDate());
                p1.setEndDate(project.getEndDate());

                projectRepository.deleteById(project.getId());
                projectRepository.save(p1);
            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return p1;
    }

    @Override
    public void deleteProject(Integer id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        }
    }


}
