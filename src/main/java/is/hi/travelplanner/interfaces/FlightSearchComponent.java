package is.hi.travelplanner.interfaces;

import java.time.LocalDate;
import java.util.List;
import is.hi.travelplanner.model.Flight;

public interface FlightSearchComponent {
    List<Flight> searchFlights(String destination, LocalDate travelDate);
    boolean book(String itemID);
    boolean cancel(String itemID);
}