package is.hi.travelplanner.interfaces;

import java.util.List;
import is.hi.travelplanner.model.HotelRoom;

public interface HotelSearchComponent {
    List<HotelRoom> searchAvailableRooms(String location, int minimumBeds);
    List<String> getAvailableLocations();
    boolean bookRoom(String roomID);
}