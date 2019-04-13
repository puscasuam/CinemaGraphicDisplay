package Tests.Service;

import Domain.*;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.ClientService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientServiceTest {
    private IValidator<Client> clintValidator = new ClientValidator();
    private IRepository<Client> clientIRepository = new InMemoryRepository<>(clintValidator);
    private ClientService clientService = new ClientService(clientIRepository);


    @Test
    void addOrUpdate() {
        clientService.addOrUpdate(1, "1234567891234", 0, "Popescu", "Ionel", "12.04.1989", "12.12.2018");
        assertEquals(1, clientService.getAll().size());

        clientService.addOrUpdate(1, "1234567891243", 0, "Popescu", "Ionel", "12.04.1989", "12.12.2018");
        assertEquals(1, clientService.getAll().size());

        clientService.addOrUpdate(2, "1234567891263", 10, "Vasilescu", "Mihai", "12.04.1989", "12.12.2018");
        assertEquals(2, clientService.getAll().size());
    }

    @Test
    void remove() {
        clientService.addOrUpdate(1, "1234567891234", 0, "Popescu", "Ionel", "12.04.1989", "12.12.2018");
        assertEquals(1, clientService.getAll().size());

        clientService.remove(1);
        assertEquals(0, clientService.getAll().size());
    }

    @Test
    void getAll() {
        clientService.addOrUpdate(1, "1234567891234", 0, "Popescu", "Ionel", "12.04.1989", "12.12.2018");
        assertEquals(1, clientService.getAll().size());

        clientService.addOrUpdate(2, "1234567891263", 10, "Vasilescu", "Mihai", "12.04.1989", "12.12.2018");
        assertEquals(2, clientService.getAll().size());
    }

    @Test
    void fullTextSearch() {

        clientService.addOrUpdate(1, "1234567891234", 0, "Popescu", "Ionel", "12.04.1989", "12.12.2018");
        assertEquals(1, clientService.getAll().size());

        clientService.addOrUpdate(2, "1234567897263", 10, "Vasilescu", "Mihai", "12.04.1989", "12.12.2018");
        assertEquals(2, clientService.getAll().size());


        String[] searchedWords = {"Popescu"};
        List<Integer> searchResults = clientService.textSearch(searchedWords);
        assertEquals(1, searchResults.get(0));
    }

    @Test
    void sortedByFidelityPoints() {

        clientService.addOrUpdate(1, "1234567891234", 0, "Popescu", "Ionel", "12.04.1989", "12.12.2018");
        clientService.addOrUpdate(2, "1234567897263", 10, "Vasilescu", "Mihai", "12.04.1989", "12.12.2018");

        List<Client> sortedDescByFidelityPoints = clientService.sortedByFidelityPoints();

        assertEquals(1, sortedDescByFidelityPoints.get(1).getId());
    }

    @Test
    void getClientsBetweenTwoDatesOfBirth() {
        clientService.addOrUpdate(1, "1234567891234", 0, "Popescu", "Paul", "12.04.1989", "12.12.2018");
        clientService.addOrUpdate(2, "1234567897263", 10, "Vasilescu", "Vasile", "13.02.1989", "12.12.2018");
        clientService.addOrUpdate(3, "2134567897263", 20, "Ionescu", "Ion", "01.07.1992", "12.12.2018");

        String startDate = "01.01.1989";
        String endDate = "12.05.1990";

        List<Integer> clientsBetweenTwoDatesOfBirth = clientService.getClientsBetweenTwoDatesOfBirth(startDate, endDate);
        assertEquals(2, clientsBetweenTwoDatesOfBirth.size());
    }

    @Test
    void updateFidelityPointDependingOnDateOfBirth() {
        clientService.addOrUpdate(1, "2234567891234", 0, "Popescu", "Paul", "12.04.1989", "12.12.2018");
        clientService.addOrUpdate(2, "2234567897263", 10, "Vasilescu", "Vasile", "13.02.1989", "12.12.2018");
        clientService.addOrUpdate(3, "2134567897263", 20, "Ionescu", "Ion", "01.07.1992", "12.12.2018");

        String startDate = "01.01.1989";
        String endDate = "12.05.1990";
        Integer fidelityPoints = 10;

        List<Client> updateFidelityPoints = clientService.updateFidelityPointDependingOnDateOfBirth(fidelityPoints, startDate, endDate);
        assertEquals(10, updateFidelityPoints.get(0).getFidelityPoints());
    }


}