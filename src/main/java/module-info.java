module com.example.theopenmoviedatabse {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;

    opens com.example.theopenmoviedatabse to javafx.fxml, com.google.gson;
    exports com.example.theopenmoviedatabse;
}