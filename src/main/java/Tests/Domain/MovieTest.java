package Tests.Domain;

import Domain.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {
    private Movie MovieTest = new Movie(1, "firstMovie", "12.12.12", 25.0, false);

    @Test
    void getIDShouldReturnCorrectID(){
        assertEquals(1, MovieTest.getId());
    }

    @Test
    void setIDShouldSetCorrectly(){
        MovieTest.setId(2);
        assertEquals(2, MovieTest.getId());
    }

    @Test
    void getTitleShouldReturnCorrectTitle() {
        assertEquals("firstMovie", MovieTest.getTitle());
    }

    @Test
    void setTitleShouldSetCorrectly() {
        MovieTest.setTitle("nameTest");
        assertEquals("nameTest", MovieTest.getTitle());
    }

    @Test
    void getReleaseDateShouldReturnCorrectReleseDate() {
        assertEquals("12.12.12", MovieTest.getReleaseDate());
    }

    @Test
    void setReleaseDateShouldSetCorrectly() {
        MovieTest.setReleaseDate("05.12.18");
        assertEquals("05.12.18", MovieTest.getReleaseDate());
    }

    @Test
    void getPriceShouldGetCorrectPrice() {
        assertEquals(25.0, MovieTest.getPrice());
    }

    @Test
    void setPriceShouldSetCorrectly() {
        MovieTest.setPrice(12.5);
        assertEquals(12.5, MovieTest.getPrice());
    }

    @Test
    void isOnCinemaShouldReturnCorrectly() {
        assertFalse(MovieTest.isOnCinema());
    }

    @Test
    void setOnCinemaShouldSetCorrectly() {
        MovieTest.setOnCinema(true);
        assertTrue(MovieTest.isOnCinema());
    }

}
