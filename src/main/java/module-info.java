module is.hi.travelplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens is.hi.travelplanner to javafx.fxml;

    opens is.hi.travelplanner.view to javafx.fxml;

    exports is.hi.travelplanner;
}