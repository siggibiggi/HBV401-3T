package is.hi.travelplanner.controllers;

import java.time.LocalDate;
import java.util.List;
import is.hi.travelplanner.interfaces.FlightSearchComponent;
import is.hi.travelplanner.model.Flight;

public class TripPlannerController {

    private FlightSearchComponent flightService;

    public TripPlannerController(FlightSearchComponent flightService) {
        this.flightService = flightService;
    }

    public List<Flight> searchFlightsForTrip(String destination, LocalDate date) {
        return flightService.searchFlights(destination, date);
    }

    public boolean bookTripItem(String itemID) {
        return flightService.book(itemID);
    }
}