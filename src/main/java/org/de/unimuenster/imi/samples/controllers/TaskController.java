package org.de.unimuenster.imi.samples.controllers;

import org.de.unimuenster.imi.samples.models.Project;
import org.de.unimuenster.imi.samples.models.Task;
import org.de.unimuenster.imi.samples.repositories.ProjectRepository;
import org.de.unimuenster.imi.samples.services.ProjectService;
import org.de.unimuenster.imi.samples.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    private TaskService taskService;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    public TaskController(TaskService taskService   ) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        List<Task> allTasks = taskService.viewAllTask();
        model.addAttribute("todos", allTasks);
        return "index";
    }


    @RequestMapping(path = "/todos/add", method = RequestMethod.GET)
    public  String addTodo(Model model){
        Task t1 = new Task();
        List<Project> projects= projectRepository.findAll();
        model.addAttribute("projects", projects);
        model.addAttribute("newTodo", t1);
        return "add";
    }
    @RequestMapping(path = "/todos", method = RequestMethod.POST)
    public String saveTodo(Task task) {
        taskService.saveTask(task);
        return "redirect:/";
    }

    @RequestMapping(path = "/todo/{id}", method = RequestMethod.GET)
    public String viewTodo(Model model, @PathVariable Integer id) {
        model.addAttribute("todoById", taskService.viewTask(id));
        return "view-todo";
    }

    @RequestMapping(path = "/todos/status", method = RequestMethod.GET)
    public String viewTodoByStatus(Model model, @RequestParam(value = "search", required = false) String status) {
        model.addAttribute("todos", taskService.viewByStatus(status));
        return "index";
    }

    @RequestMapping(path = "/todo/edit/{id}", method = RequestMethod.GET)
    public String editTodo(Model model, @PathVariable Integer id) {
        model.addAttribute("editTodo", taskService.findTask(id));
        return "edit";
    }

    @RequestMapping(path = "/todo/delete/{id}", method = RequestMethod.GET)
    public String deleteTodo(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
}