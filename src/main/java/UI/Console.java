package UI;

import CustomException.DataFormatException;
import Domain.Client;
import Domain.Movie;
import Domain.MovieFrequenciesOnReservationViewModel;
import Domain.Reservation;
import Service.ClientService;
import Service.MovieService;
import Service.ReservationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {

    private MovieService movieService;
    private ClientService clientService;
    private ReservationService reservationService;

    private Scanner scanner;

    public Console(MovieService movieService, ClientService clientService, ReservationService reservationService) {
        this.movieService = movieService;
        this.clientService = clientService;
        this.reservationService = reservationService;
        this.scanner = new Scanner(System.in);
    }

    private void showMenu() {
        System.out.println("1. Movie CRUD");
        System.out.println("2. Client CRUD");
        System.out.println("3. Reservation CRUD");
        System.out.println("4. Search in movie/client fields");
        System.out.println("5. Show reservations time frame report");
        System.out.println("6. Show movie frequencies on reservations report");
        System.out.println("7. Show client cards sorted by fidelity points");
        System.out.println("8. Delete all reservation between two dates");
        System.out.println("9. Increment fidelity points for clients with date of birth between two dates");
        System.out.println("x. Exit");
    }

    public void run() {
        while (true) {
            showMenu();

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    runMovieCrud();
                    break;
                case "2":
                    runClientCrud();
                    break;
                case "3":
                    runReservationCrud();
                    break;
                case "4":
                    handleSearchFullText();
                    break;
                case "5":
                    handleReservationTimeFrame();
                    break;
                case "6":
                    handleMovieFrequencies();
                    break;
                case "7":
                    handleSortClientCardsByFidelityPoints();
                    break;
                case "8":
                    handleDeleteReservationBetweenTwoDates();
                    break;
                case "9":
                    handleIncrementationofFidelityPoints();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }



    private void runReservationCrud() {
        while (true) {
            System.out.println("1. Add or update a reservation");
            System.out.println("2. Remove a reservation");
            System.out.println("3. View all reservation");
            System.out.println("4. Back");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddUpdateReservation();
                    break;
                case "2":
                    handleRemoveReservation();
                    break;
                case "3":
                    handleViewReservations();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void handleViewReservations() {
        for (Reservation transaction : reservationService.getAll()) {
            System.out.println(transaction);
        }
    }

    private void handleRemoveReservation() {
        try {
            System.out.print("Enter the id to remove:");
            int id = Integer.parseInt(scanner.nextLine());
            reservationService.remove(id);

            System.out.println("Reservation removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + '[' + ex.getClass() + ']' + ex.getMessage());
        }
    }

    private void handleAddUpdateReservation() {
        try {
            System.out.print("Enter id: ");
            int id = scanner.nextInt();
            System.out.print("Enter movie id (0 to not change for update): ");
            int movieId = scanner.nextInt();
            System.out.print("Enter client card (-1 for unknown client card and 0 to not change for update): ");
            int idClient = scanner.nextInt();
            System.out.print("Enter date (empty to not change for update): ");
            scanner.nextLine();
            String date = scanner.nextLine();
            System.out.print("Enter time (empty to not change for update): ");
            String time = scanner.nextLine();

            reservationService.addOrUpdate(id, movieId, idClient, date, time);

            System.out.println("Reservation added!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + '[' + ex.getClass() + ']' + ex.getMessage());
        }
    }

    private void runClientCrud() {
        while (true) {
            System.out.println("1. Add or update a client");
            System.out.println("2. Remove a client");
            System.out.println("3. View all clients");
            System.out.println("4. Back");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddUpdateClient();
                    break;
                case "2":
                    handleRemoveClient();
                    break;
                case "3":
                    handleViewClients();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void handleViewClients() {
        for (Client client : clientService.getAll()) {
            System.out.println(client);
        }
    }

    private void handleRemoveClient() {
        try {
            System.out.print("Enter the id to remove:");
            int id = scanner.nextInt();
            clientService.remove(id);

            System.out.println("Client removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + '[' + ex.getClass() + ']' + ex.getMessage());
        }
    }

    private void handleAddUpdateClient() {
        try {
            System.out.print("Enter id: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter CNP (empty to not change for update): ");
            String CNP = scanner.nextLine();
            System.out.print("Enter fidelity points : ");
            int fidelityPoints = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter last name (empty to not change for update): ");
            String lastName = scanner.nextLine();
            System.out.print("Enter first name (empty to not change for update): ");
            String firstName = scanner.nextLine();
            System.out.print("Enter date of birth (empty to not change for update): ");
            String dateOfBirth = scanner.nextLine();
            System.out.print("Enter date of registration (empty to not change for update): ");
            String dateOfRegistration = scanner.nextLine();

            clientService.addOrUpdate(id, CNP, fidelityPoints, lastName, firstName, dateOfBirth, dateOfRegistration);

            System.out.println("Client added!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + '[' + ex.getClass() + ']' + ex.getMessage());
        }
    }


    private void handleIncrementationofFidelityPoints() {

        System.out.println("Enter the value of incrementation:");
        int increment = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter start date (dd.mm.yyyy):");
        String startDate = scanner.nextLine();

        System.out.println("Enter end date(dd.mm.yyyy):");
        String endDate = scanner.nextLine();

        List<Client> updateFidelityPointDependingOnDateOfBirth = clientService.updateFidelityPointDependingOnDateOfBirth(increment, startDate, endDate);

        if (!updateFidelityPointDependingOnDateOfBirth.isEmpty()) {
            for (Client client : updateFidelityPointDependingOnDateOfBirth) {
                System.out.println(client);
            }
        } else {
            System.out.println("There are no clients with birthdays between selected dates!");
        }

    }


    private void runMovieCrud() {
        while (true) {
            System.out.println("1. Add or update a movie");
            System.out.println("2. Remove a movie");
            System.out.println("3. View all movies");
            System.out.println("4. Back");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddUpdateMovie();
                    break;
                case "2":
                    handleRemoveMovie();
                    break;
                case "3":
                    handleViewMovies();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void handleViewMovies() {
        for (Movie movie : movieService.getAll()) {
            System.out.println(movie);
        }
    }

    private void handleRemoveMovie() {
        try {
            System.out.print("Enter the id to remove:");
            int id = Integer.parseInt(scanner.nextLine());
            movieService.remove(id);
            System.out.println("Movie removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + '[' + ex.getClass() + ']' + ex.getMessage());
        }
    }

    private void handleAddUpdateMovie() {

        try {
            System.out.print("Enter id: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter title (empty to not change for update): ");
            String title = scanner.nextLine();
            System.out.print("Enter releaseDate (empty to not change for update): ");
            String releaseDate = scanner.nextLine();
            System.out.print("Enter price (0 to not change for update): ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter on cinema (true / false): ");
            boolean onCinema = Boolean.parseBoolean(scanner.nextLine());

            movieService.addOrUpdate(id, title, releaseDate, price, onCinema);

            System.out.println("Movie added!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + '[' + ex.getClass() + ']' + ex.getMessage());
        }
    }

    private void handleSearchFullText() {
        System.out.println("Enter search words:");
        String[] words = scanner.nextLine().split("\\s");

        List<Integer> searchInClients = new ArrayList<>();
        List<Integer> searchInMovies = new ArrayList<>();

        searchInClients = clientService.textSearch(words);
        searchInMovies = movieService.textSearch(words);

        if (!searchInClients.isEmpty()) {
            System.out.println();
            System.out.println("Searching results in clients fields: ");
            for (Integer searchId : searchInClients) {
                for (Client client : clientService.getAll()) {
                    if (searchId == client.getId()) {
                        System.out.println(client);
                    }
                }
            }
        }


        if (!searchInMovies.isEmpty()) {
            System.out.println();
            System.out.println("Searching results in movie fields: ");
            for (Integer searchId : searchInMovies) {
                for (Movie movie : movieService.getAll()) {
                    if (searchId == movie.getId()) {
                        System.out.println(movie);
                    }
                }
            }
        }
    }

    private void handleReservationTimeFrame() throws DataFormatException {
        List<Integer> reservationsTimeFrame = new ArrayList<>();

        System.out.println("Enter first limit of time interval(hh:mm):");
        String startLimit = scanner.nextLine();

        System.out.println("Enter second limit of time interval(hh:mm):");
        String endLimit = scanner.nextLine();

        try {
            reservationsTimeFrame = reservationService.reservationsTimeFrameReport(startLimit, endLimit);
        } catch (Exception ex) {
            System.out.println("Errors:\n" + '[' + ex.getClass() + ']' + ex.getMessage());
        }

        if (!reservationsTimeFrame.isEmpty()) {
            System.out.println();
            System.out.println("Searching results in a given time frame: ");
            for (Integer searchId : reservationsTimeFrame) {
                for (Reservation reservation : reservationService.getAll()) {
                    if (searchId == reservation.getId()) {
                        System.out.println(reservation);
                    }
                }
            }
        } else {
            System.out.println("There is no reservation in selected time frame");
        }
    }


    private void handleMovieFrequencies() {
        List<MovieFrequenciesOnReservationViewModel> movieFrequencies = reservationService.getMovieFrequenciesReport();
        for (MovieFrequenciesOnReservationViewModel frequencieReport : movieFrequencies) {
            System.out.println(String.format("%s %d", frequencieReport.getTitle(), frequencieReport.getFrequencies()));
        }
    }

    private void handleSortClientCardsByFidelityPoints() {
        for (Client client : clientService.sortedByFidelityPoints()) {
            System.out.println(client);
        }
    }

    private void handleDeleteReservationBetweenTwoDates() {
        System.out.println("Enter start date (dd.mm.yyyy):");
        String startDate = scanner.nextLine();

        System.out.println("Enter end date(dd.mm.yyyy):");
        String endDate = scanner.nextLine();

        List<Reservation> reservationsDateInterval = reservationService.deleteReservationsBetweenTwoDates(startDate, endDate);

        if (!reservationsDateInterval.isEmpty()) {
            for (Reservation reservation : reservationsDateInterval) {
                System.out.println(reservation);
            }
        } else {
            System.out.println("There is no reservation in selected time interval!");
        }
    }

}