package com.example.todoappfx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
// Made this comment to check if committing to the main rep works or not -RJ
import java.sql.*;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("To-Do List");

        displayTasks(primaryStage);
        //displayEnterTaskScreen(primaryStage);
        //displayLoginScreen(primaryStage);
    }



    private void displayLoginScreen(Stage primaryStage){
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #a81f1f, #d0a02b);");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        Label orLabel = new Label("OR");
        Label ifLabel = new Label("if you are here for the first time.");

        // Apply styles to UI components
        usernameField.getStyleClass().add("field");
        passwordField.getStyleClass().add("field");
        usernameLabel.getStyleClass().add("label");
        passwordLabel.getStyleClass().add("label");
        loginButton.getStyleClass().add("button_blue");
        orLabel.getStyleClass().add("label");
        registerButton.getStyleClass().add("button_blue");
        ifLabel.getStyleClass().add("label");

        // Add components to the VBox
        root.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, orLabel, registerButton, ifLabel);

        // Create the scene and apply styles
        Scene scene = new Scene(root, 600, 400);
        URL cssFile = getClass().getResource("/com/example/todoappfx/common.css");
        scene.getStylesheets().add(cssFile.toExternalForm());

        loginButton.setOnAction(actionEvent -> {
//            if(data from database is correct){
//                AlertBox.display("Nice", "Login Successful.");
//            } else{
//                AlertBox.display("Sorry!", "Please check username and/or password.");
//            }
        });

        registerButton.setOnAction(actionEvent -> displayRegisterScreen(primaryStage));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayRegisterScreen(Stage primaryStage) {
        primaryStage.close();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log an error or exit the program)
            return;
        }



        VBox root = new VBox(7);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #4a78ad, #508d3d);");

        Label enterDetailsForRegisteringLabel = new Label("Please enter details for registering.");
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        Label locationLabel = new Label("Location:");
        Button registerButton = new Button("Register");
        Button loginMaybeButton = new Button("❮ Login");
        Label loginMaybeLabel = new Label("If you already have an account: ");
        TextField regLoginIdField = new TextField();
        TextField regPasswordIdField = new TextField();
        TextField regLocationField = new TextField();

        //enterDetailsForRegisteringLabel.getStyleClass().add("label");
        VBox.setMargin(enterDetailsForRegisteringLabel, new Insets(0, 0, 30, 0)); // top, right, bottom, left
        VBox.setMargin(registerButton, new Insets(0, 0, 30, 0)); // top, right, bottom, left
        VBox.setMargin(regLocationField, new Insets(0, 0, 25, 0)); // top, right, bottom, left
        usernameLabel.getStyleClass().add("label");
        passwordLabel.getStyleClass().add("label");
        locationLabel.getStyleClass().add("label");
        registerButton.getStyleClass().add("button_blue");
        loginMaybeButton.getStyleClass().add("button_blue");
        loginMaybeLabel.getStyleClass().add("label");
        regLoginIdField.getStyleClass().add("field");
        regPasswordIdField.getStyleClass().add("field");
        regLocationField.getStyleClass().add("field");


        root.getChildren().addAll(enterDetailsForRegisteringLabel, usernameLabel, regLoginIdField, passwordLabel, regPasswordIdField,locationLabel,regLocationField,
                registerButton, loginMaybeLabel, loginMaybeButton
        );

        loginMaybeButton.setOnAction(actionEvent -> displayLoginScreen(primaryStage));

        registerButton.setOnAction(actionEvent -> {
            AlertBox.display("Yay!", "Registration Successful!");
            //data goes to database
        });

        Scene scene = new Scene(root, 700, 500);

        URL cssFile = getClass().getResource("/com/example/todoappfx/common.css");
        scene.getStylesheets().add(cssFile.toExternalForm());


        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private static List<Tasks> fetchTasksFromDatabase() {
        List<Tasks> tasksList = new ArrayList<>();

        // JDBC URL, username, and password of MySQL server
        String jdbcUrl = "jdbc:mysql://localhost:3306/to_do_list_db";
        String username = "root";
        String password = "somanysqls";

        // SQL query to select tasks from the database
        String selectQuery = "SELECT * FROM task_details";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                // Retrieve task details from the result set
                String title = resultSet.getString("title");
                String taskDescription = resultSet.getString("description");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                int taskID = resultSet.getInt("taskID");
                boolean completed = resultSet.getBoolean("Completed");

                // Create a Tasks object and add it to the list
                Tasks task = new Tasks(title, taskDescription, date, time, taskID, completed);
                tasksList.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQL exception (e.g., log an error or show a message to the user)
        }

        return tasksList;
    }
    private void displayTasks(Stage primaryStage) {
        // Assuming you have a method to fetch tasks from the database
        List<Tasks> tasksList = fetchTasksFromDatabase();

        // Convert the list to an observable list
        ObservableList<Tasks> observableTasks = FXCollections.observableArrayList(tasksList);

        // Create a ListView and set the items
        ListView<Tasks> listView = new ListView<>(observableTasks);

        // Set a custom cell factory to display tasks as you want
        listView.setCellFactory(param -> new TaskListCell());

        // Create the scene and apply styles
        VBox root = new VBox(listView);
        root.setStyle("-fx-background-color: #FFFFFF;");  // Set your desired background color

        // Create a ScrollPane and set its content to the root VBox
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Set the scene content to the ScrollPane
        primaryStage.setScene(new Scene(scrollPane, 600, 400));

        primaryStage.show();
    }


    // Custom cell factory to display tasks in the ListView
    // Custom cell factory to display tasks in the ListView
    private static class TaskListCell extends ListCell<Tasks> {
        @Override
        protected void updateItem(Tasks task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setText(null);
                setGraphic(null);
            } else {
                // Create UI components
                CheckBox checkBox = new CheckBox("Completed");
                Button deleteButton = new Button("➖");

                // Customize how each task is displayed in the ListView
                Label detailsLabel = new Label(
                        "Title: " + task.getTitle() +
                                "\nDescription: " + task.getTask_description() +
                                "\nDate: " + task.getDate() +
                                "\nTime: " + task.getTime()
                );

                // Set actions for the checkbox and delete button
                checkBox.setSelected(task.isCompleted());
                checkBox.setOnAction(event -> completedOrNot(task.getTaskID(), checkBox));

                deleteButton.setOnAction(event -> {
                    // Assuming you have a deleteTask method to remove the task from the database
                    deleteTask(task.getTaskID());
                    // Assuming you have a method to fetch updated tasks from the database
                    List<Tasks> updatedTasks = fetchTasksFromDatabase();
                    // Convert the updated list to observable list
                    ObservableList<Tasks> updatedObservableTasks = FXCollections.observableArrayList(updatedTasks);
                    // Update the ListView items
                    ((ListView<Tasks>) getListView()).setItems(updatedObservableTasks);
                });

                // Add UI components to the cell in an HBox
                HBox hbox = new HBox(10, detailsLabel, checkBox, deleteButton);
                setGraphic(hbox);
            }
        }
    }





    private void displayEnterTaskScreen(Stage primaryStage) {
        // JDBC URL, username, and password of MySQL server
        String jdbcUrl = "jdbc:mysql://localhost:3306/to_do_list_db";
        String username = "root";
        String password = "somanysqls";

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #8c2626, #d0a02b);");

        Label titleLabel = new Label("Title:");
        TextField titleField = new TextField();
        Label taskDescriptionLabel = new Label("Task Description:");
        TextField taskDescriptionField = new TextField();
        Label dueDateLabel = new Label("What is the due date of task completion(YYYY-MM-DD):");
        TextField dueDateField = new TextField();
        Label dueTimeLabel = new Label("What is the due time of task completion(optional):");
        TextField dueTimeField = new TextField();
        Button backButton = new Button("❮");
        Button addTaskButton = new Button("Add Task");

        // Apply styles to UI components
        titleField.getStyleClass().add("field");
        taskDescriptionField.getStyleClass().add("field");
        titleLabel.getStyleClass().add("label");
        taskDescriptionLabel.getStyleClass().add("label");
        dueDateLabel.getStyleClass().add("label");
        dueDateField.getStyleClass().add("field");
        dueTimeLabel.getStyleClass().add("label");
        dueTimeField.getStyleClass().add("field");
        backButton.getStyleClass().add("button_blue");
        addTaskButton.getStyleClass().add("button_blue");

        // Add components to the VBox
        root.getChildren().addAll(titleLabel, titleField, taskDescriptionLabel, taskDescriptionField, dueDateLabel, dueDateField, dueTimeLabel, dueTimeField, addTaskButton, backButton);

        // Create the scene and apply styles
        Scene scene = new Scene(root, 600, 400);
        URL cssFile = getClass().getResource("/com/example/todoappfx/common.css");
        scene.getStylesheets().add(cssFile.toExternalForm());

        backButton.setOnAction(actionEvent -> displayTasks(primaryStage));

        addTaskButton.setOnAction(actionEvent -> {
            // Add value to the database
            String a = taskDescriptionField.getText();
            String b = titleField.getText();
            String c = dueDateField.getText();
            String d = dueTimeField.getText();

            // Generate a random TaskID
            int randomTaskID = Math.abs(UUID.randomUUID().hashCode());

            // Establish a connection
            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                // Create a PreparedStatement
                String createTableSQL = "INSERT INTO task_details (taskID, title, description, date, Completed, time) VALUES (?, ?, ?, ?, false, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
                    preparedStatement.setInt(1, randomTaskID);
                    preparedStatement.setString(2, b);
                    preparedStatement.setString(3, a);
                    preparedStatement.setString(4, c);

                    // Check if due time is provided
                    if (!d.isEmpty()) {
                        // Parse the due time and set it using setTime
                        LocalTime dueTime = LocalTime.parse(d);
                        preparedStatement.setTime(5, java.sql.Time.valueOf(dueTime));
                    } else {
                        // If no due time is provided, set it to null in the database
                        preparedStatement.setNull(5, Types.TIME);
                    }

                    preparedStatement.executeUpdate();
                    System.out.println("Task added successfully.");
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the SQL exception (e.g., log an error or show a message to the user)
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the SQL exception (e.g., log an error or show a message to the user)
            }

            AlertBox.display("Hurray!", "Your task is saved.");
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //to check if task is completed or not using the checkbox
    private static void completedOrNot(int TaskID, CheckBox checkBox){

        // JDBC URL, username, and password of MySQL server
        String jdbcUrl = "jdbc:mysql://localhost:3306/to_do_list_db";
        String username = "root";
        String password = "somanysqls";


        if (checkBox.isSelected()) {
            // Establish a connection
            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                // Create a PreparedStatement
                String createTableSQL = "UPDATE task_details\n" +
                        "SET Completed = true\n" +
                        "WHERE taskID = " + TaskID;
                try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
                    preparedStatement.executeUpdate();
                    System.out.println("Task added successfully.");
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the SQL exception (e.g., log an error or show a message to the user)
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the SQL exception (e.g., log an error or show a message to the user)
            }
            System.out.println("Task with TaskID" + TaskID + "Completed");
        } else {
            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                // Create a PreparedStatement
                String createTableSQL = "UPDATE task_details\n" +
                        "SET Completed = false\n" +
                        "WHERE taskID = " + TaskID;
                try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
                    preparedStatement.executeUpdate();
                    System.out.println("Task added successfully.");
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the SQL exception (e.g., log an error or show a message to the user)
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the SQL exception (e.g., log an error or show a message to the user)
            }
            System.out.println("Task Incomplete");
        }
    }
    private static void deleteTask(int TaskID){
        String jdbcUrl = "jdbc:mysql://localhost:3306/to_do_list_db";
        String username = "root";
        String password = "somanysqls";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Create a PreparedStatement
            String createTableSQL = "DELETE FROM task_details WHERE taskID = " + TaskID;
            try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
                preparedStatement.executeUpdate();
                System.out.println("Task added successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the SQL exception (e.g., log an error or show a message to the user)
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQL exception (e.g., log an error or show a message to the user)
        }
    }
    public static void main(String[] args) {
        launch();
    }

}
