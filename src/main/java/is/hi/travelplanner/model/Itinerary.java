package is.hi.travelplanner.model;

import java.util.ArrayList;
import java.util.List;

public class Itinerary {
    public double totalPrice = 0.0;

    private final List<Flight> flights = new ArrayList<>();
    private final List<HotelRoom> rooms = new ArrayList<>();
    private final List<DayTour> tours = new ArrayList<>();

    public void addFlight(Flight flight) { flights.add(flight); recalculateTotal(); }
    public void addRoom(HotelRoom room) { rooms.add(room); recalculateTotal(); }
    public void addTour(DayTour tour) { tours.add(tour); recalculateTotal(); }

    public void removeFlight(int index) { flights.remove(index); recalculateTotal(); }
    public void removeRoom(int index) { rooms.remove(index); recalculateTotal(); }
    public void removeTour(int index) { tours.remove(index); recalculateTotal(); }

    public void clear() {
        flights.clear();
        rooms.clear();
        tours.clear();
        recalculateTotal();
    }

    public List<Flight> getFlights() { return flights; }
    public List<HotelRoom> getRooms() { return rooms; }
    public List<DayTour> getTours() { return tours; }

    private void recalculateTotal() {
        double sum = 0;
        for (Flight f : flights) sum += f.getPrice();
        for (HotelRoom r : rooms) sum += r.getPrice();
        for (DayTour t : tours) sum += t.getPrice();
        this.totalPrice = sum;
    }
}