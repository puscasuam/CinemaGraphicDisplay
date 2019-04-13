package Tests.Service;

import CustomException.NonExistingEntityException;
import Domain.*;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.ClientService;
import Service.MovieService;
import Service.ReservationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ReservationServiceTest {
    private IValidator<Movie> movieValidator = new MovieValidator();
    private IRepository<Movie> movieRepository = new InMemoryRepository<>(movieValidator);
    private MovieService movieService = new MovieService(movieRepository);

    private IValidator<Client> clintValidator = new ClientValidator();
    private IRepository<Client> clientIRepository = new InMemoryRepository<>(clintValidator);
    private ClientService clientService = new ClientService(clientIRepository);

    private IValidator<Reservation> reservationIValidator = new ReservationValidator();
    private IRepository<Reservation> reservationIRepository = new InMemoryRepository<>(reservationIValidator);
    private ReservationService reservationService = new ReservationService(movieRepository, clientIRepository, reservationIRepository);


    @Test
    void addOrUpdate() {
        movieService.addOrUpdate(1, "movie1", "12.12.2019", 15.0, true);
        clientService.addOrUpdate(1, "1234567891234", 0, "Popescu", "Ionel", "12.04.1989", "12.12.2018");

        Reservation reservation = new Reservation(1, 1, 1, "12.12.2019", "12:34");
        reservationService.addOrUpdate(1, 1, 1, "12.12.2019", "12:34");
        assertEquals(1, reservationService.getAll().size());
        assertDoesNotThrow(() -> reservationIValidator.validate(reservation));


//        Reservation reservation2 = new Reservation(1, 4, 1, "12.12.2019", "12:34");
//        reservationService.addOrUpdate(1, 4, 1, "12.12.2019", "12:34");
//        assertEquals(1, reservationService.getAll().size());
//        assertThrows(NonExistingEntityException.class, () -> reservationIValidator.validate(reservation2));

    }


}
