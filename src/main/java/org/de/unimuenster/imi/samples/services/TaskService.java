package org.de.unimuenster.imi.samples.services;

import org.de.unimuenster.imi.samples.models.Task;

import java.util.List;
public interface TaskService {

    Task saveTask(Task task);

    Task viewTask(Integer id);

    List<Task> viewByStatus(String status);

    Task findTask(Integer id);

    Task update(Task task);

    void deleteTask(Integer id);

    List<Task> viewAllTask();


}

