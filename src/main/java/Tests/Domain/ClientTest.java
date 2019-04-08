package Tests.Domain;

import Domain.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private Client ClientTest = new Client(1, "1234567891234", 10, "Popescu", "Ion", "12.03.1989", "12.12.2018" );

    @Test
    void getIDShouldReturnCorrectID(){
        assertEquals(1, ClientTest.getId());
    }

    @Test
    void setIDShouldSetCorrectly(){
        ClientTest.setId(2);
        assertEquals(2, ClientTest.getId());
    }

    @Test
    void getCNPShouldReturnCorrectCNP(){
        assertEquals("1234567891234", ClientTest.getCNP());
    }

    @Test
    void setCNPShouldSetCorrectly(){
        ClientTest.setCNP("1234567894321");
        assertEquals("1234567894321", ClientTest.getCNP());
    }


    @Test
    void getFidelityPointsShouldReturnCorrectFidelityPoints() {
        assertEquals(10, ClientTest.getFidelityPoints());
    }

    @Test
    void setFidelityPointsShouldSetCorrectly() {
        ClientTest.setFidelityPoints(20);
        assertEquals(20, ClientTest.getFidelityPoints());
    }


    @Test
    void getLastNameShouldReturnCorrectLastName() {
        assertEquals("Popescu", ClientTest.getLastName());
    }

    @Test
    void setLastNameShouldSetCorrectly() {
        ClientTest.setLastName("Muntean");
        assertEquals("Muntean", ClientTest.getLastName());
    }

    @Test
    void getFirstNameShouldReturnCorrectFirstName() {
        assertEquals("Ion", ClientTest.getFirstName());
    }

    @Test
    void setFirstNameShouldSetCorrectly() {
        ClientTest.setFirstName("Mihai");
        assertEquals("Mihai", ClientTest.getFirstName());
    }

    @Test
    void getDateOfBirthShouldReturnCorrectDateOfBirth() {
        assertEquals("12.03.1989", ClientTest.getDateOfBirth());
    }

    @Test
    void setDateOfBirthShouldSetCorrectly() {
        ClientTest.setDateOfBirth("05.12.2019");
        assertEquals("05.12.2019", ClientTest.getDateOfBirth());
    }

    @Test
    void getDateOfRegistrationhShouldReturnCorrectDateOfRegistration() {
        assertEquals("12.12.2018", ClientTest.getDateOfRegistration());
    }

    @Test
    void setDateOfRegistrationShouldSetCorrectly() {
        ClientTest.setDateOfRegistration("05.12.2019");
        assertEquals("05.12.2019", ClientTest.getDateOfRegistration());
    }

}