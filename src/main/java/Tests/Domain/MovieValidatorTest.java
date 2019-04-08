package Tests.Domain;

import Domain.IValidator;
import Domain.Movie;
import Domain.MovieValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieValidatorTest {

    private IValidator<Movie> validatorMovie = new MovieValidator();

    @Test
    void movieShouldBeValid() {

        Movie movie = new Movie(1, "Hotel Mumbai", "01.04.2019", 10.0, true);

        try {
            validatorMovie.validate(movie);
        } catch (RuntimeException rex) {
           fail("Should never get here");
        }
        assertTrue(true);
    }


    @Test
    void movieIdShouldNotBeValid() {

        Movie movie = new Movie(-1, "Hotel Mumbai", "01.04.2019", 10.0, true);

        try {
            validatorMovie.validate(movie);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }

    @Test
    void movieTitleEmptyShouldNotBeValid() {

        Movie movie = new Movie(1, "", "01.04.2019", 10.0, true);

        try {
            validatorMovie.validate(movie);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }

    @Test
    void movieTitleNullShouldNotBeValid() {

        Movie movie = new Movie(1, null, "01.04.2019", 10.0, true);

        try {
            validatorMovie.validate(movie);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }

    @Test
    void movieReleaseDateShouldNotBeValid() {

        Movie movie = new Movie(1, "Hotel Mumbai", "01042019", 10.0, true);

        try {
            validatorMovie.validate(movie);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }

    @Test
    void moviePriceNegativeShouldNotBeValid() {

        Movie movie = new Movie(1, "Hotel Mumbai", "01.04.2019", -10.0, true);

        try {
            validatorMovie.validate(movie);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }

    @Test
    void moviePriceZeroShouldNotBeValid() {

        Movie movie = new Movie(1, "Hotel Mumbai", "01.04.2019", 0.0, true);

        try {
            validatorMovie.validate(movie);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }
}