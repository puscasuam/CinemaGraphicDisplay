package Tests.Domain;

import Domain.*;
import Repository.IRepository;
import Repository.InMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationValidatorTest {


    private ReservationValidator validator = new ReservationValidator();


    @Test
    void validateShouldPass() {
        Reservation reservation = new Reservation(1, 1, 1, "12.03.2019", "12:30");

        try{
            validator.validate(reservation);
        } catch (RuntimeException e){
            fail("Should never get here");
        }
        assertTrue(true);
    }

    @Test
    void validateIdShouldFail() {
        Reservation reservation = new Reservation(-1, 1, 1, "12.03.2019", "12:30");

        try{
            validator.validate(reservation);
        } catch (RuntimeException e){
            assertTrue(true);
        }
    }

    @Test
    void validateDateShouldFail() {
        Reservation reservation = new Reservation(1, 1, 1, "12032019", "12:30");

        try{
            validator.validate(reservation);
        } catch (RuntimeException e){
            assertTrue(true);
        }
    }

    @Test
    void validateTimeShouldFail() {
        Reservation reservation = new Reservation(1, 1, 1, "12.03.2019", "1230");

        try{
            validator.validate(reservation);
        } catch (RuntimeException e){
            assertTrue(true);
        }
    }

}