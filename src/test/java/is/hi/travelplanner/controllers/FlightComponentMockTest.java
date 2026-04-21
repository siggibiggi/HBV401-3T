package is.hi.travelplanner.controllers;

import is.hi.travelplanner.mock.FlightComponentMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import is.hi.travelplanner.model.Flight;

public class FlightComponentMockTest {

    private FlightComponentMock mockFlightComponent;

    @BeforeEach
    public void setUp() {
        mockFlightComponent = new FlightComponentMock();
    }

    @AfterEach
    public void tearDown() {
        mockFlightComponent = null;
    }

    @Test
    public void testSearchFlights_ReturnsMockedData() {
        LocalDate testDate = LocalDate.of(2026, 5, 1);

        List<Flight> result = mockFlightComponent.searchFlights("Akureyri", testDate);

        assertFalse(result.isEmpty(), "Result should not be empty");
        assertEquals("Reykjavík", result.get(0).getDepartureName());
    }

    @Test
    public void testSearchFlights_NoResults() {
        LocalDate testDate = LocalDate.of(2026, 5, 1);

        List<Flight> result = mockFlightComponent.searchFlights("London", testDate);

        assertTrue(result.isEmpty(), "Result should be empty");
    }

    @Test
    public void testBookFlight_Success() {
        boolean result = mockFlightComponent.book("F-100");
        assertTrue(result, "should return true");
    }
}