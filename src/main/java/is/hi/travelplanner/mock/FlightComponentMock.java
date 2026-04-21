package is.hi.travelplanner.mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import is.hi.travelplanner.interfaces.FlightSearchComponent;
import is.hi.travelplanner.model.Flight;

/**
 * a mock FlightSearchComponent used to test the TripPlannerController
 * hardcodes a single test: searching for "Akureyri" returns flight "F-100"
 * booking or canceling that specific flight will always succeed
 */
public class FlightComponentMock implements FlightSearchComponent {

    @Override
    public List<Flight> searchFlights(String destination, LocalDate travelDate) {
        List<Flight> flights = new ArrayList<>();

        if ("Akureyri".equalsIgnoreCase(destination)) {
            flights.add(new Flight(
                    "F-100",
                    25000.0,
                    "Reykjavík",
                    "Akureyri",
                    LocalDateTime.of(travelDate.getYear(), travelDate.getMonth(), travelDate.getDayOfMonth(), 10, 0),
                    LocalDateTime.of(travelDate.getYear(), travelDate.getMonth(), travelDate.getDayOfMonth(), 11, 0),
                    50,
                    false
            ));
        }
        return flights;
    }

    @Override
    public boolean book(String itemID) {
        return "F-100".equals(itemID);
    }

    @Override
    public boolean cancel(String itemID) {
        return "F-100".equals(itemID);
    }

    @Override
    public List<String> getAvailableDestinations() {
        return List.of("Akureyri", "Reykjavík", "London");
    }
}