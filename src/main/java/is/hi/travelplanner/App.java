package is.hi.travelplanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import is.hi.travelplanner.adapters.*;
import is.hi.travelplanner.controllers.TripPlannerController;

import repository.FlightRepository;
import controller.FlightController;
import software.controller.HotelController;
import controller.TourController;
import database.TourDB;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FlightController fController = new FlightController(new FlightRepository());
        HotelController hController = new HotelController();
        TourController dController = new TourController(new TourDB());

        FlightAdapter flightAdapter = new FlightAdapter(fController);
        HotelAdapter hotelAdapter = new HotelAdapter(hController);
        TourAdapter tourAdapter = new TourAdapter(dController);

        TripPlannerController mainController = new TripPlannerController(flightAdapter, hotelAdapter, tourAdapter);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
        loader.setController(mainController);

        BorderPane root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("travel planner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}