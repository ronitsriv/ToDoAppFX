module com.example.todoappfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.todoappfx to javafx.fxml;
    exports com.example.todoappfx;
}