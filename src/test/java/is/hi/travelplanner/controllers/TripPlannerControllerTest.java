package is.hi.travelplanner.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import is.hi.travelplanner.mock.FlightComponentMock;
import is.hi.travelplanner.model.Flight;

public class TripPlannerControllerTest {

    private TripPlannerController controller;
    private FlightComponentMock mockFlightComponent;

    @BeforeEach
    public void setUp() {
        mockFlightComponent = new FlightComponentMock();
        controller = new TripPlannerController(mockFlightComponent);
    }

    @AfterEach
    public void tearDown() {
        controller = null;
        mockFlightComponent = null;
    }

    @Test
    public void testSearchFlights_ReturnsMockedData() {
        LocalDate testDate = LocalDate.of(2026, 5, 1);
        List<Flight> result = controller.searchFlightsForTrip("Akureyri", testDate);

        assertEquals(1, result.size());
        assertEquals("F-100", result.get(0).flightID);
        assertEquals("Reykjavík", result.get(0).departureName);
        assertEquals(25000.0, result.get(0).price, 0.01);
    }

    @Test
    public void testSearchFlights_NoResults() {
        LocalDate testDate = LocalDate.of(2026, 5, 1);
        List<Flight> result = controller.searchFlightsForTrip("London", testDate);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testBookFlight_Success() {
        boolean result = controller.bookTripItem("F-100");
        assertTrue(result);
    }
}