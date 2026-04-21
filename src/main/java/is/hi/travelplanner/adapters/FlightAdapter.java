package is.hi.travelplanner.adapters;

import is.hi.travelplanner.interfaces.FlightSearchComponent;
import is.hi.travelplanner.model.Flight;
import controller.FlightController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightAdapter implements FlightSearchComponent {

    private final FlightController flightController;

    public FlightAdapter(FlightController flightController) {
        this.flightController = flightController;
    }

    @Override
    public List<Flight> searchFlights(String destination, LocalDate travelDate) {
        List<Flight> frontendFlights = new ArrayList<>();
        List<model.Flight> backendFlights = flightController.searchFlights(destination, travelDate);

        for (model.Flight f : backendFlights) {
            frontendFlights.add(new Flight(
                    f.getFlightID(),
                    f.getPrice(),
                    f.getDepartureName(),
                    f.getArrivalName(),
                    f.getDepartureTime(),
                    f.getArrivalTime(),
                    f.getAvailableSeats(),
                    f.isBookingStatus()
            ));
        }
        return frontendFlights;
    }

    @Override
    public boolean book(String itemID) {
        return flightController.book(itemID);
    }

    @Override
    public boolean cancel(String itemID) {
        return flightController.cancel(itemID);
    }

    @Override
    public List<String> getAvailableDestinations() {
        return flightController.getAllFlights().stream()
                .map(model.Flight::getArrivalName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}