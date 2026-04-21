package is.hi.travelplanner.interfaces;

import java.util.List;
import is.hi.travelplanner.model.DayTour;

public interface TourSearchComponent {
    List<DayTour> searchTours(String query);
    List<String> getAvailableTourTypes();
    boolean bookTour(String tourID, String travelerName, String phone, String email, int tickets);
}