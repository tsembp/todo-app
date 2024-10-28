package controller;

import model.Task;
import model.TaskList;
import java.util.List;

public class TaskController {
    private TaskList taskList;

    public TaskController() {
        taskList = new TaskList();
    }

    public void addTask(String description) {
        taskList.addTask(description);
    }

    public void completeTask(int index) {
        taskList.completeTask(index);
    }

    public void removeTask(int index) {
        taskList.removeTask(index);
    }

    public List<Task> getTasks() {
        return taskList.getTasks();
    }

}
