package is.hi.travelplanner.model;

import java.time.LocalDateTime;

public class Flight {
    public String flightID;
    public double price;
    public String departureName;
    public String arrivalName;
    public LocalDateTime departureTime;
    public LocalDateTime arrivalTime;
    public int availableSeats;
    public boolean bookingStatus;

    public Flight(String flightID, double price, String departureName, String arrivalName,
                  LocalDateTime departureTime, LocalDateTime arrivalTime, int availableSeats, boolean bookingStatus) {
        this.flightID = flightID;
        this.price = price;
        this.departureName = departureName;
        this.arrivalName = arrivalName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
        this.bookingStatus = bookingStatus;
    }

    public String getFlightID() { return flightID; }
    public double getPrice() { return price; }
    public String getDepartureName() { return departureName; }
    public String getArrivalName() { return arrivalName; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public int getAvailableSeats() { return availableSeats; }
    public boolean isBookingStatus() { return bookingStatus; }
}