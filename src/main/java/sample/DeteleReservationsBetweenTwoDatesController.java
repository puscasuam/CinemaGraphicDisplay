package sample;

import Domain.Reservation;
import Service.ReservationService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class DeteleReservationsBetweenTwoDatesController {
    public TableView tableViewReservations;
    public TableColumn tableColumnId;
    public TableColumn tableColumnMovieID;
    public TableColumn tableColumnClientID;
    public TableColumn tableColumnDate;
    public TableColumn tableColumnTime;
    public TextField txtReservationsTFFirstDate;
    public TextField txtReservationsTFSecondDate;
    public Button btnDeleteReservationsBetweenTwoDates;


    private ReservationService reservationService;


    public void setServices(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    private ObservableList<Reservation> reservationsDeleted = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            reservationsDeleted.addAll(reservationService.getAll());
            tableViewReservations.setItems(reservationsDeleted);
        });
    }


    public void btnDeleteReservationsBetweenTwoDatesClick(ActionEvent actionEvent) {
        try {
            String startDate = txtReservationsTFFirstDate.getText();
            String endDate = txtReservationsTFSecondDate.getText();

            List<Reservation> reservationsDateInterval = reservationService.deleteReservationsBetweenTwoDates(startDate, endDate);
            reservationsDeleted.clear();

            for (Reservation reservation : reservationsDateInterval) {
                reservationsDeleted.addAll(reservationService.getAll());
            }


        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }

    }

}
