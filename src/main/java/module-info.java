module com.example.hbv401g {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hbv401g to javafx.fxml;
    exports com.example.hbv401g;
}