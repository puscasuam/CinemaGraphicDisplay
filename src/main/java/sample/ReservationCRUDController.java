package sample;

import Domain.Reservation;
import Service.ReservationService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class ReservationCRUDController {
    public TableView tableViewReservations;
    public TableColumn tableColumnId;
    public TableColumn tableColumnMovieID;
    public TableColumn tableColumnClientID;
    public TableColumn tableColumnDate;
    public TableColumn tableColumnTime;
    public TextField txtReservationID;
    public TextField txtReservationMovieId;
    public TextField txtReservationClientId;
    public TextField txtReservationDate;
    public TextField txtReservationTime;
    public Button btnAddReservation;
    public Button btnDeleteReservation;


    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();
    private ReservationService reservationService;

    public void setService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            reservations.addAll(reservationService.getAll());
            tableViewReservations.setItems(reservations);
        });
    }


    public void btnAddReservationClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reservation has been added or updated");
        alert.showAndWait();

        try {
            int id = Integer.parseInt(txtReservationID.getText());
            int movieId = Integer.parseInt(txtReservationMovieId.getText());
            int idClient = Integer.parseInt(txtReservationClientId.getText());
            String date = txtReservationDate.getText();
            String time = txtReservationTime.getText();


            reservationService.addOrUpdate(id, movieId, idClient, date, time);

            reservations.clear();
            reservations.addAll(reservationService.getAll());
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void btnDeleteReservationClick(ActionEvent actionEvent) {
        Reservation selectedResevation = (Reservation) tableViewReservations.getSelectionModel().getSelectedItem();

        if (selectedResevation != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                try {
                    reservationService.remove(selectedResevation.getId());
                    reservations.clear();
                    reservations.addAll(reservationService.getAll());
                } catch (RuntimeException rex) {
                    Common.showValidationError(rex.getMessage());
                }
            }
        }
    }


}

