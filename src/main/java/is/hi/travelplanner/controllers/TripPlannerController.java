package is.hi.travelplanner.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

import is.hi.travelplanner.interfaces.*;
import is.hi.travelplanner.model.*;

public class TripPlannerController {

    private final FlightSearchComponent flightService;
    private final HotelSearchComponent hotelService;
    private final TourSearchComponent tourService;

    private final Itinerary currentItinerary = new Itinerary();

    // --- FXML: Flights ---
    @FXML private ComboBox<String> flightDestCombo;
    @FXML private DatePicker flightDateInput;
    @FXML private TableView<Flight> flightTable;
    @FXML private TableColumn<Flight, String> colFlightId, colDeparture, colArrival;
    @FXML private TableColumn<Flight, Double> colFlightPrice;

    // --- FXML: Hotels ---
    @FXML private ComboBox<String> hotelLocCombo;
    @FXML private TextField hotelBedsInput;
    @FXML private TableView<HotelRoom> hotelTable;
    @FXML private TableColumn<HotelRoom, String> colHotelName, colRoomType;
    @FXML private TableColumn<HotelRoom, Double> colRoomPrice;

    // --- FXML: Tours ---
    @FXML private ComboBox<String> tourQueryCombo;
    @FXML private TableView<DayTour> tourTable;
    @FXML private TableColumn<DayTour, String> colTourName, colTourCompany;
    @FXML private TableColumn<DayTour, Double> colTourPrice;

    // --- FXML: Itinerary ---
    @FXML private ListView<String> itineraryListView;
    @FXML private Label totalPriceLabel;

    public TripPlannerController(FlightSearchComponent flightService,
                                 HotelSearchComponent hotelService,
                                 TourSearchComponent tourService) {
        this.flightService = flightService;
        this.hotelService = hotelService;
        this.tourService = tourService;
    }

    @FXML
    public void initialize() {
        flightDestCombo.getItems().addAll(flightService.getAvailableDestinations());
        hotelLocCombo.getItems().addAll(hotelService.getAvailableLocations());
        tourQueryCombo.getItems().addAll(tourService.getAvailableTourTypes());

        colFlightId.setCellValueFactory(new PropertyValueFactory<>("flightID"));
        colDeparture.setCellValueFactory(new PropertyValueFactory<>("departureName"));
        colArrival.setCellValueFactory(new PropertyValueFactory<>("arrivalName"));
        colFlightPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        colHotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colRoomPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        colTourName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTourCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colTourPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    public void onSearchFlights() {
        String dest = flightDestCombo.getValue();
        LocalDate date = flightDateInput.getValue();
        if (dest != null && date != null) {
            List<Flight> results = flightService.searchFlights(dest, date);
            flightTable.setItems(FXCollections.observableArrayList(results));
        }
    }

    @FXML
    public void onSearchHotels() {
        String loc = hotelLocCombo.getValue();
        int beds = hotelBedsInput.getText().isEmpty() ? 1 : Integer.parseInt(hotelBedsInput.getText());
        if (loc != null && !loc.isEmpty()) {
            List<HotelRoom> results = hotelService.searchAvailableRooms(loc, beds);
            hotelTable.setItems(FXCollections.observableArrayList(results));
        }
    }

    @FXML
    public void onSearchTours() {
        String query = tourQueryCombo.getValue();
        if (query != null && !query.isEmpty()) {
            List<DayTour> results = tourService.searchTours(query);
            tourTable.setItems(FXCollections.observableArrayList(results));
        }
    }

    // --- Add to Itinerary Actions ---
    @FXML
    public void onAddFlightToItinerary() {
        Flight selected = flightTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            currentItinerary.addFlight(selected);
            updateItineraryView();
        }
    }

    @FXML
    public void onAddRoomToItinerary() {
        HotelRoom selected = hotelTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            currentItinerary.addRoom(selected);
            updateItineraryView();
        }
    }

    @FXML
    public void onAddTourToItinerary() {
        DayTour selected = tourTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            currentItinerary.addTour(selected);
            updateItineraryView();
        }
    }

    private void updateItineraryView() {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Flight f : currentItinerary.getFlights()) items.add("Flight: " + f.flightID + " - $" + f.price);
        for (HotelRoom r : currentItinerary.getRooms()) items.add("Hotel: " + r.hotelName + " - $" + r.price);
        for (DayTour t : currentItinerary.getTours()) items.add("Tour: " + t.name + " - $" + t.price);

        itineraryListView.setItems(items);
        totalPriceLabel.setText(String.format("Total: $%.2f", currentItinerary.totalPrice));
    }

    @FXML
    public void onRemoveFromItinerary() {
        int idx = itineraryListView.getSelectionModel().getSelectedIndex();
        if (idx < 0) return; // Nothing selected

        int numFlights = currentItinerary.getFlights().size();
        int numRooms = currentItinerary.getRooms().size();

        if (idx < numFlights) {
            currentItinerary.removeFlight(idx);
        } else if (idx < numFlights + numRooms) {
            currentItinerary.removeRoom(idx - numFlights);
        } else {
            currentItinerary.removeTour(idx - numFlights - numRooms);
        }
        updateItineraryView();
    }

    @FXML
    public void onBookItinerary() {
        if (currentItinerary.getFlights().isEmpty() && currentItinerary.getRooms().isEmpty() && currentItinerary.getTours().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Your itinerary is empty!");
            alert.showAndWait();
            return;
        }

        boolean allSuccess = true;

        for (Flight f : currentItinerary.getFlights()) {
            if (!flightService.book(f.getFlightID())) allSuccess = false;
        }
        for (HotelRoom r : currentItinerary.getRooms()) {
            if (!hotelService.bookRoom(r.getRoomID())) allSuccess = false;
        }
        for (DayTour t : currentItinerary.getTours()) {
            if (!tourService.bookTour(t.getTourID(), "John Doe", "555-1234", "john@example.com", 1)) allSuccess = false;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (allSuccess) {
            alert.setHeaderText("booking confirmed");
            alert.setContentText("booking successful");
            currentItinerary.clear();
            updateItineraryView();
        } else {
            alert.setHeaderText("partial failure");
            alert.setContentText("didn't work, loser");
        }
        alert.showAndWait();
    }
}