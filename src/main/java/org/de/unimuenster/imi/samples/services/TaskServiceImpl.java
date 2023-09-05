package org.de.unimuenster.imi.samples.services;

import org.de.unimuenster.imi.samples.models.Project;
import org.de.unimuenster.imi.samples.models.Task;
import org.de.unimuenster.imi.samples.repositories.ProjectRepository;
import org.de.unimuenster.imi.samples.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task saveTask(Task task){
        Task todo = taskRepository.save(task);
        try{
            todo.setTitle(task.getTitle());
            todo.setDescription(task.getDescription());
            todo.setProject(task.getProject());
            Date time = new Date();
            long newTime = time.getTime();
            Timestamp completedTime = new Timestamp(newTime);
            String status = task.getStatus();
            if (status.equalsIgnoreCase("completed")){
                todo.setStatus(status);
                todo.setCompletedAt(completedTime);
            }
            else{
                todo.setStatus(status);
                todo.setCompletedAt(null);
            }
            taskRepository.save(todo);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return todo;
    }


    public Task viewTask(Integer id) {
        Task task = null;
        try {
            task = findTask(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return task;
    }

    public List<Task> viewAllTask() {
        List<Task> t = null;
        try {
            t = taskRepository.findAll();
            System.out.println(t);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return t;
    }

    public Task findTask(Integer id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            return taskRepository.save(task);
        }
        return null;
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    public void deleteTask(Integer id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        }
    }

    public List<Task> viewByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

}
