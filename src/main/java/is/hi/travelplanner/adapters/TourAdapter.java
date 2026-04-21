package is.hi.travelplanner.adapters;

import is.hi.travelplanner.interfaces.TourSearchComponent;
import is.hi.travelplanner.model.DayTour;
import controller.TourController;

import java.util.ArrayList;
import java.util.List;

public class TourAdapter implements TourSearchComponent {

    private final TourController tourController;

    public TourAdapter(TourController tourController) {
        this.tourController = tourController;
    }

    @Override
    public List<DayTour> searchTours(String query) {
        List<DayTour> frontendTours = new ArrayList<>();

        List<model.Tour> backendTours = tourController.search(query);

        for (model.Tour dTour : backendTours) {
            frontendTours.add(new DayTour(
                    dTour.getTourID(),
                    dTour.getTourName(),
                    "Local Guide",
                    dTour.getPrice(),
                    5.0,
                    dTour.getStartTime(),
                    dTour.getLength(),
                    true,
                    !dTour.isFull()
            ));
        }
        return frontendTours;
    }

    @Override
    public boolean bookTour(String tourID, String travelerName, String phone, String email, int tickets) {
        try {
            model.Booking booking = tourController.bookWithNewTraveler(tourID, travelerName, phone, email, tickets);
            return booking != null;
        } catch (Exception e) {
            System.err.println("booking failed: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<String> getAvailableTourTypes() {
        return tourController.getAllTourTypes();
    }
}