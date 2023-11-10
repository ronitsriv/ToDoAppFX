package com.example.todoappfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.net.URL;
import java.sql.Statement;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("To Do List");

        displayTasks(primaryStage);
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

    private void displayTasks(Stage primaryStage){
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, black, #d0a02b);");

        VBox taskDetailBox = new VBox(5);
        taskDetailBox.setPadding(new Insets(10));

        Label usernameLabel = new Label("Tasks");
        TextField usernameField = new TextField();
        Button plusButton = new Button("➕");


        // Apply styles to UI components
        usernameField.getStyleClass().add("field");
        usernameLabel.getStyleClass().add("label");
        plusButton.getStyleClass().add("button_blue");



        // Add components to the VBox
        root.getChildren().addAll(usernameLabel, plusButton);

        // Create the scene and apply styles
        Scene scene = new Scene(root, 600, 400);
        URL cssFile = getClass().getResource("/com/example/todoappfx/common.css");
        scene.getStylesheets().add(cssFile.toExternalForm());

        plusButton.setOnAction(actionEvent -> {
//            if(data from database is correct){
//                AlertBox.display("Nice", "Login Successful.");
//            } else{
//                AlertBox.display("Sorry!", "Please check username and/or password.");
//            }
        });

        plusButton.setOnAction(actionEvent -> displayEnterTaskScreen(primaryStage));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayEnterTaskScreen(Stage primaryStage){
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #8c2626, #d0a02b);");

        Label titleLabel = new Label("Title:");
        TextField titleField = new TextField();
        Label taskDescriptionLabel = new Label("Task Description:");
        TextField taskDescriptionField = new TextField();
        Button backButton = new Button("❮");
        Button addTaskButton = new Button("Add Task");

        // Apply styles to UI components
        titleField.getStyleClass().add("field");
        taskDescriptionField.getStyleClass().add("field");
        titleLabel.getStyleClass().add("label");
        taskDescriptionLabel.getStyleClass().add("label");
        backButton.getStyleClass().add("button_blue");
        addTaskButton.getStyleClass().add("button_blue");


        // Add components to the VBox
        root.getChildren().addAll(titleLabel, titleField, taskDescriptionLabel, taskDescriptionField, addTaskButton,backButton);

        // Create the scene and apply styles
        Scene scene = new Scene(root, 600, 400);
        URL cssFile = getClass().getResource("/com/example/todoappfx/common.css");
        scene.getStylesheets().add(cssFile.toExternalForm());

        backButton.setOnAction(actionEvent -> {
//            if(data from database is correct){
//                AlertBox.display("Nice", "Login Successful.");
//            } else{
//                AlertBox.display("Sorry!", "Please check username and/or password.");
//            }
        });

        backButton.setOnAction(actionEvent -> displayTasks(primaryStage));

        addTaskButton.setOnAction(actionEvent -> {
            //Add value to database
            AlertBox.display("Hurray!","Your task is saved.");
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log an error or exit the program)
            return;
        }

        // JDBC URL, username, and password of MySQL server
        String jdbcUrl = "jdbc:mysql://localhost:3306/newschema";
        String username = "root";
        String password = "somanysqls";

        // Establish a connection
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Create a Statement object
            try (Statement statement = connection.createStatement()) {
                // Define the SQL statement to create a new table
                String createTableSQL = "CREATE TABLE IF NOT EXISTS new_table ("
                        + "id INT PRIMARY KEY AUTO_INCREMENT,"
                        + "name VARCHAR(255) NOT NULL,"
                        + "age INT"
                        + ")";

                // Execute the SQL statement to create the table
                statement.executeUpdate(createTableSQL);

                System.out.println("Table 'new_table' created successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the SQL exception (e.g., log an error or show a message to the user)
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQL exception (e.g., log an error or show a message to the user)
        }
    }
}
