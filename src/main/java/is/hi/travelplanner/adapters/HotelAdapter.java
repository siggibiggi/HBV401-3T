package is.hi.travelplanner.adapters;

import is.hi.travelplanner.interfaces.HotelSearchComponent;
import is.hi.travelplanner.model.HotelRoom;
import software.controller.HotelController;
import software.model.Hotel;
import software.model.Room;

import java.util.ArrayList;
import java.util.List;

public class HotelAdapter implements HotelSearchComponent {

    private final HotelController hotelController;

    public HotelAdapter(HotelController hotelController) {
        this.hotelController = hotelController;
    }

    @Override
    public List<HotelRoom> searchAvailableRooms(String location, int minimumBeds) {
        List<HotelRoom> frontendRooms = new ArrayList<>();

        List<Hotel> matchingHotels = hotelController.searchHotels(location, null, minimumBeds);

        for (Hotel hotel : matchingHotels) {
            List<Room> backendRooms = hotelController.getAvailableRoomsForHotel(hotel.getHotelName(), minimumBeds);

            for (Room r : backendRooms) {
                frontendRooms.add(new HotelRoom(
                        r.getRoomId(),
                        hotel.getHotelName(),
                        r.getRoomType(),
                        r.getNumberOfBeds(),
                        r.getPricePerDay(),
                        !r.getIsBooked()
                ));
            }
        }
        return frontendRooms;
    }

    @Override
    public boolean bookRoom(String roomID) {
        return hotelController.bookRoom(roomID);
    }

    @Override
    public List<String> getAvailableLocations() {
        return hotelController.getAllHotels().stream()
                .map(software.model.Hotel::getHotelLocation)
                .distinct()
                .sorted()
                .collect(java.util.stream.Collectors.toList());
    }
}