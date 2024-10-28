package view;

import controller.TaskController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Task;
import javafx.geometry.Insets;

public class TodoApp extends Application {
    private TaskController controller;

    @Override
    public void start(Stage primaryStage) {
        controller = new TaskController();

        // Create GUI components
        GridPane layout = new GridPane();
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));

        ListView<String> taskListView = new ListView<>();
        TextField taskInput = new TextField();
        Button addButton = new Button("Add Task");
        Button completeButton = new Button("Complete Task");
        Button removeButton = new Button("Remove Task");

        // Add Task Action
        addButton.setOnAction(e -> {
            String taskDescription = taskInput.getText();
            if (!taskDescription.isEmpty()) {
                controller.addTask(taskDescription);
                taskInput.clear();
                updateTaskList(taskListView);
            }
        });

        // Complete Task Action
        completeButton.setOnAction(e -> {
            int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                controller.completeTask(selectedIndex);
                updateTaskList(taskListView);
                taskInput.clear(); // Clear the input field after completing a task
            }
        });

        // Remove Task Action
        removeButton.setOnAction(e -> {
            int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this task?");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        controller.removeTask(selectedIndex);
                        updateTaskList(taskListView);
                        taskInput.clear(); // Clear the input field after removing a task
                    }
                });
            }
        });

        // Set up layout
        layout.add(taskListView, 0, 0, 2, 1); // Span 2 columns
        layout.add(taskInput, 0, 1);
        layout.add(addButton, 1, 1);
        layout.add(completeButton, 0, 2);
        layout.add(removeButton, 1, 2);
        
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("To-Do List");
        primaryStage.show();
    }

    // Update task list in GUI
    private void updateTaskList(ListView<String> taskListView) {
        taskListView.getItems().clear();
        for (Task task : controller.getTasks()) {
            taskListView.getItems().add(task.toString());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
