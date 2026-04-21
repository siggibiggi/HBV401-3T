package is.hi.travelplanner.model;

public class DayTour {
    public String tourID;
    public String name;
    public String company;
    public double price;
    public double rating;
    public String startTime;
    public int duration;
    public boolean hasPickup;
    public boolean bookingStatus;

    public DayTour(String tourID, String name, String company, double price, double rating,
                   String startTime, int duration, boolean hasPickup, boolean bookingStatus) {
        this.tourID = tourID;
        this.name = name;
        this.company = company;
        this.price = price;
        this.rating = rating;
        this.startTime = startTime;
        this.duration = duration;
        this.hasPickup = hasPickup;
        this.bookingStatus = bookingStatus;
    }

    public String getTourID() { return tourID; }
    public String getName() { return name; }
    public String getCompany() { return company; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }
    public String getStartTime() { return startTime; }
    public int getDuration() { return duration; }
    public boolean isHasPickup() { return hasPickup; }
    public boolean isBookingStatus() { return bookingStatus; }
}