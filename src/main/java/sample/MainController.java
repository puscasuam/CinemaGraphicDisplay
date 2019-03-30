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

            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
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

            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
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

            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
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
}
