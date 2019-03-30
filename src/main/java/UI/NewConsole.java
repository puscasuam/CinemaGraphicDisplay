package UI;

import Domain.Client;
import Domain.Movie;
import Domain.Reservation;
import Service.ClientService;
import Service.MovieService;
import Service.ReservationService;

import java.util.Scanner;

public class NewConsole {

    private MovieService movieService;
    private ClientService clientService;
    private ReservationService reservationService;
    private Scanner scanner;


    public NewConsole(MovieService movieService, ClientService clientService, ReservationService reservationService) {
        this.movieService = movieService;
        this.clientService = clientService;
        this.reservationService = reservationService;
        this.scanner = new Scanner(System.in);
    }

    private void showMenu() {
        System.out.println("Your option:");
        System.out.println("add/update movie,id,title,release date(dd:mm:yy),price,on cinema(true/false)");
        System.out.println("remove movie,id");
        System.out.println("showall movies");
        System.out.println();
        System.out.println("add/update client,id,CNP,fidelity points,last name,first name,date of birth (dd:mm:yy),date of registration (dd:mm:yy)");
        System.out.println("remove client,id");
        System.out.println("showall clients");
        System.out.println();
        System.out.println("add/update reservation,id,movie id,id client,date(dd.mm.yy),time(hh:mm)");
        System.out.println("remove reservation,id");
        System.out.println("showall reservations");
        System.out.println();
        System.out.println("x. Exit");
    }


    private String[] splitString(String string) {
        String[] parts = string.split(",");
        return parts;
    }

    public void run() {
        while (true) {
            showMenu();

            String option = scanner.nextLine();
            String[] entry = splitString(option);
            if (entry[0] != null) {
                switch (entry[0]) {
                    case "add/update movie":
                        handleAddUpdateMovie(entry);
                        break;

                    case "remove movie":
                        handleRemoveMovie(entry);
                        break;

                    case "showall movies":
                        handleViewMovies(entry);
                        break;

                    case "add/update client":
                        handleAddUpdateClient(entry);
                        break;

                    case "remove client":
                        handleRemoveClient(entry);
                        break;

                    case "showall clients":
                        handleViewClients(entry);
                        break;

                    case "add/update reservation":
                        handleAddUpdateReservation(entry);
                        break;

                    case "remove reservation":
                        handleRemoveReservation(entry);
                        break;

                    case "showall reservations":
                        handleViewReservations(entry);
                        break;

                    case "x":
                        return;
                    default:
                        System.out.println("Invalid option!");
                        break;
                }
            } else {
                System.out.println("Invalid option! Try again!");
            }

        }
    }

    private void handleViewReservations(String[] entry) {
        if (entry.length == 1) {
            for (Reservation reservation : reservationService.getAll()) {
                System.out.println(reservation);
            }
        } else {
            System.out.println("Invalid option!");
        }

    }

    private void handleRemoveReservation(String[] entry) {
        try {
            if (entry.length == 2) {
                int id = Integer.parseInt(entry[1]);
                reservationService.remove(id);

                System.out.println("Reservation removed!");
            } else {
                throw new RuntimeException("Invalid option!");
            }
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }


    private void handleAddUpdateReservation(String[] entry) {
        try {
            if (entry.length == 6) {
                int id = Integer.parseInt(entry[1]);
                int movieId = Integer.parseInt(entry[2]);
                int idClient = Integer.parseInt(entry[3]);
                String date = entry[4];
                String time = entry[5];

                reservationService.addOrUpdate(id, movieId, idClient, date, time);
            } else {
                throw new RuntimeException("Invalid option!");
            }
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }


    private void handleViewClients(String[] entry) {
        if (entry.length == 1) {
            for (Client client : clientService.getAll()) {
                System.out.println(client);
            }
        } else {
            System.out.println("Invalid option!");
        }
    }


    private void handleRemoveClient(String[] entry) {
        try {
            if (entry.length == 2) {
                int id = Integer.parseInt(entry[1]);
                clientService.remove(id);

                System.out.println("Client removed!");
            } else {
                throw new RuntimeException("Invalid option!");
            }
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddUpdateClient(String[] entry) {
        try {
            if (entry.length == 8) {
                int id = Integer.parseInt(entry[1]);
                String CNP = entry[2];
                int fidelityPoints = Integer.parseInt(entry[3]);
                String lastName = entry[4];
                String firstName = entry[5];
                String dateOfBirth = entry[6];
                String dateOfRegistration = entry[7];

                clientService.addOrUpdate(id, CNP, fidelityPoints, lastName, firstName, dateOfBirth, dateOfRegistration);

                System.out.println("Client added!");
            } else {
                throw new RuntimeException("Invalid option!");
            }
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }


    private void handleViewMovies(String[] entry) {
        if (entry.length == 1) {
            for (Movie movie : movieService.getAll()) {
                System.out.println(movie);
            }
        } else {
            System.out.println("Invalid option!");
        }
    }

    private void handleRemoveMovie(String[] entry) {
        try {
            if (entry.length == 2) {
                int id = Integer.parseInt(entry[1]);
                movieService.remove(id);
                System.out.println("Movie removed!");
            } else {
                throw new RuntimeException("Invalid option!");
            }
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddUpdateMovie(String[] entry) {

        try {
            if (entry.length == 6) {
                int id = Integer.parseInt(entry[1]);
                String title = entry[2];
                String releaseDate = entry[3];
                double price = Double.parseDouble(entry[4]);
                boolean onCinema = Boolean.valueOf(entry[5]);

                movieService.addOrUpdate(id, title, releaseDate, price, onCinema);

                System.out.println("Movie added!");
            } else {
                throw new RuntimeException("Invalid option!");
            }

        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }
}