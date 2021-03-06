package com.codurance.training.tasks.service;

import com.codurance.training.tasks.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addProject(String projectName) {
        taskRepository.getTasks().put(projectName, new ArrayList<Task>());
    }

    public Collection<String> findAllProjects() {
        return taskRepository.getTasks().keySet();
    }

    public Collection<Task> findAllTasksForProject(String projectName) {
        Map<String, List<Task>> projectNameToTasksMap = taskRepository.getTasks();
        return projectNameToTasksMap.get(projectName);
    }

    public ActionResult addTaskToProject(String project, String description) {
        if (taskRepository.projectWithNameExists(project)) {
            Collection<Task> projectTasks = findAllTasksForProject(project);
            Task task = new Task(taskRepository.nextId(), description);
            projectTasks.add(task);
            return new ActionSuccessful(task.getId());
        }
        return new ActionFailed();
    }

    public Task findTaskById(long taskId) {
        Collection<Task> allTasks = taskRepository.findAllTasks();
        for (Task task : allTasks) {
            if (task.getId() == taskId) {
                return task;
            }
        }
        return null;
    }
}
