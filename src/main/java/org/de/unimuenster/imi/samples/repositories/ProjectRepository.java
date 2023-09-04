package org.de.unimuenster.imi.samples.repositories;

import org.de.unimuenster.imi.samples.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}