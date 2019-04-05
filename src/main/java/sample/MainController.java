package sample;

import Service.ClientService;
import Service.MovieService;
import Service.ReservationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {
    public Button btnMovieCRUD;
    public Button btnClientCRUD;
    public Button btnReservationCRUD;
    public Button btnFullTextSearch;
    public Button btnReservationsTimeFrame;
    public Button btnMoviesOrderedByBookings;
    public Button btnClientsOrderedByFidelityPoints;
    public Button btnDeleteReservationBetweenTwoDates;

    private MovieService movieService;
    private ClientService clientService;
    private ReservationService reservationService;


    public void setServices(MovieService movieService, ClientService clientService, ReservationService reservationService) {
        this.movieService = movieService;
        this.clientService = clientService;
        this.reservationService = reservationService;
    }

    public void btnMovieCRUDClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/MovieCRUD.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            Stage stage = new Stage();
            stage.setTitle("Movie CRUD");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            MovieCRUDController controller = fxmlLoader.getController();
            controller.setService(movieService);

            stage.showAndWait();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Movie CRUD.", e);
        }

    }

    public void btnClientCRUDClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ClientCRUD.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            Stage stage = new Stage();
            stage.setTitle("Client CRUD");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            ClientCRUDController controller = fxmlLoader.getController();
            controller.setService(clientService);

            stage.showAndWait();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Client CRUD.", e);
        }

    }

    public void btnReservationCRUDClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ReservationCRUD.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            Stage stage = new Stage();
            stage.setTitle("Reservation CRUD");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            ReservationCRUDController controller = fxmlLoader.getController();
            controller.setService(reservationService);

            stage.showAndWait();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Reservation CRUD.", e);
        }
    }

    public void btnFullTextSearchClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FullTextSearch.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            Stage stage = new Stage();
            stage.setTitle("Full text search");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            FullTextSearchController controller = fxmlLoader.getController();
            controller.setServices(movieService, clientService);

            stage.showAndWait();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Full text search", e);
        }

    }


    public void btnReservationsTimeFrameClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ReservationsTimeFrame.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            Stage stage = new Stage();
            stage.setTitle("Reservations time frame report");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            ReservationsTimeFrameController controller = fxmlLoader.getController();
            controller.setServices(reservationService);

            stage.showAndWait();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Show reservations time frame report", e);
        }
    }

    public void btnShowMoviesOrderedByBookings(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/MovieReservationsFrequencies.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            Stage stage = new Stage();
            stage.setTitle("Movies report");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            ShowMoviesOrderedByReservationsController controller = fxmlLoader.getController();
            controller.setServices(reservationService);

            stage.showAndWait();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Show movies ordered by reservation", e);
        }

    }

    public void btnClientsOrderedByFidelityPointsClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ClientsOrderedByFidelityPoints.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            Stage stage = new Stage();
            stage.setTitle("Clients report");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            ShowClientsOrderedByFidelityPointsController controller = fxmlLoader.getController();
            controller.setServices(clientService);

            stage.showAndWait();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Show clients ordered by fidelity points", e);
        }
    }

    public void btnDeleteReservationBetweenTwoDatesClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/DeleteReservationsBetweenTwoDates.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            Stage stage = new Stage();
            stage.setTitle("Reservations report");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            DeteleReservationsBetweenTwoDatesController controller = fxmlLoader.getController();
            controller.setServices(reservationService);

            stage.showAndWait();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Detele all reservations between two datess", e);
        }

    }

    public void btnUpdateFidelityPointsClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/UpdateFidelityPoints.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            Stage stage = new Stage();
            stage.setTitle("Client card report");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            UpdateFidelityPointsController controller = fxmlLoader.getController();
            controller.setServices(clientService);

            stage.showAndWait();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Update fidelity points - depending on date of birth", e);
        }

    }
}