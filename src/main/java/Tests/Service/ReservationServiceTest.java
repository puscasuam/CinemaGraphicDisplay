package Tests.Service;

import Domain.*;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.ClientService;
import Service.MovieService;
import Service.ReservationService;

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
    private ReservationService reservationService = new ReservationService(movieRepository, clientIRepository,reservationIRepository);

}