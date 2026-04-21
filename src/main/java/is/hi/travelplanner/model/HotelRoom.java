package is.hi.travelplanner.model;

public class HotelRoom {
    public String roomID;
    public String hotelName;
    public String roomType;
    public int capacity;
    public double price;
    public boolean bookingStatus;

    public HotelRoom(String roomID, String hotelName, String roomType, int capacity, double price, boolean bookingStatus) {
        this.roomID = roomID;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.capacity = capacity;
        this.price = price;
        this.bookingStatus = bookingStatus;
    }

    public String getRoomID() { return roomID; }
    public String getHotelName() { return hotelName; }
    public String getRoomType() { return roomType; }
    public int getCapacity() { return capacity; }
    public double getPrice() { return price; }
    public boolean isBookingStatus() { return bookingStatus; }
}