module com.example.theopenmoviedatabse {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.theopenmoviedatabse to javafx.fxml;
    exports com.example.theopenmoviedatabse;
}