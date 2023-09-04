package org.de.unimuenster.imi.samples.services;

import org.de.unimuenster.imi.samples.models.Project;
import org.de.unimuenster.imi.samples.models.Task;

import java.util.List;

public interface ProjectService {

    Project saveProject(Project project);

    Project viewProject(Integer id);

    Project findProject(Integer id);

    Project updateProject(Project project);

    void deleteProject(Integer id);

    List<Project> viewAllProjects();


}

