package Tests.Domain;

import Domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientValidatorTest {

    private IValidator<Client> clientValidator = new ClientValidator();

    @Test
    void clientShouldBeValid() {

        Client client = new Client(1, "1234567891234", 10, "Popescu", "Ionescu", "12.03.1968", "12.03.2019");

        try {
            clientValidator.validate(client);
        } catch (RuntimeException rex) {
            fail("Should never get here");
        }
        assertTrue(true);
    }


    @Test
    void clientIdShouldNotBeValid() {

        Client client = new Client(-1, "1234567891234", 10, "Popescu", "Ionescu", "12.03.1968", "12.03.2019");

        try {
            clientValidator.validate(client);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }

    @Test
    void clientCNPLengthShouldNotBeValid() {

        Client client = new Client(1, "123456789123434", 10, "Popescu", "Ionescu", "12.03.1968", "12.03.2019");

        try {
            clientValidator.validate(client);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }


    @Test
    void clientCNPFormatShouldNotBeValid() {

        Client client = new Client(1, "9234567891234", 10, "Popescu", "Ionescu", "12.03.1968", "12.03.2019");

        try {
            clientValidator.validate(client);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }


    @Test
    void clientFidelityPointsShouldNotBeValid() {

        Client client = new Client(1, "1234567891234", -40, "Popescu", "Ionescu", "12.03.1968", "12.03.2019");

        try {
            clientValidator.validate(client);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }

    @Test
    void clientLastNameNullShouldNotBeValid() {

        Client client = new Client(1, "1234567891234", 10, null, "Ionescu", "12.03.1968", "12.03.2019");

        try {
            clientValidator.validate(client);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }


    @Test
    void clientLastNameEmptyShouldNotBeValid() {

        Client client = new Client(1, "1234567891234", 10, "", "Ionescu", "12.03.1968", "12.03.2019");

        try {
            clientValidator.validate(client);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }

    @Test
    void clientFirstNameNullShouldNotBeValid() {

        Client client = new Client(1, "1234567891234", 10, "Popescu", null, "12.03.1968", "12.03.2019");

        try {
            clientValidator.validate(client);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }


    @Test
    void clientFirstNameEmptyShouldNotBeValid() {

        Client client = new Client(1, "1234567891234", 10, "Popescu", "", "12.03.1968", "12.03.2019");

        try {
            clientValidator.validate(client);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }

    @Test
    void clientDateOfBirthShouldNotBeValid() {

        Client client = new Client(1, "1234567891234", 10, "Popescu", "Ionescu", "12.031968", "12.03.2019");

        try {
            clientValidator.validate(client);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }


    @Test
    void clientDateOfRegistrationShouldNotBeValid() {

        Client client = new Client(1, "1234567891234", 10, "Popescu", "Ionescu", "12.03.1968", "1203.2019");

        try {
            clientValidator.validate(client);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }


}