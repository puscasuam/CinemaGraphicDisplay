package Tests.Domain;

import Domain.Reservation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationTest {
    private Reservation ReservationTest = new Reservation(1, 1, 1, "03.04.2017", "12:30");

    @Test
    void getIDShouldReturnCorrectID() {
        assertEquals(1, ReservationTest.getId());
    }

    @Test
    void setIDShouldSetCorrectly() {
        ReservationTest.setId(2);
        assertEquals(2, ReservationTest.getId());
    }

    @Test
    void getMovieIdShouldReturnCorrectMovieId() {
        assertEquals(1, ReservationTest.getMovieId());
    }

    @Test
    void setMovieShouldSetCorrectly() {
        ReservationTest.setMovieId(3);
        assertEquals(3, ReservationTest.getMovieId());
    }


    @Test
    void getIdClientShouldReturnCorrectIdClient() {
        assertEquals(1, ReservationTest.getIdClient());
    }

    @Test
    void setIdClientShouldSetCorrectly() {
        ReservationTest.setIdClient(2);
        assertEquals(2, ReservationTest.getIdClient());
    }


    @Test
    void getDateShouldReturnCorrectDate() {
        assertEquals("03.04.2017", ReservationTest.getDate());
    }

    @Test
    void setDateShouldSetCorrectly() {
        ReservationTest.setDate("05.12.2019");
        assertEquals("05.12.2019", ReservationTest.getDate());
    }

    @Test
    void getTimeShouldReturnCorrectTime() {
        assertEquals("12:30", ReservationTest.getTime());
    }

    @Test
    void setTimeShouldSetCorrectly() {
        ReservationTest.setTime("13:00");
        assertEquals("13:00", ReservationTest.getTime());
    }
}