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

public class ReservationsTimeFrameController {
    public TableView tableViewReservations;
    public TableColumn tableColumnId;
    public TableColumn tableColumnMovieID;
    public TableColumn tableColumnClientID;
    public TableColumn tableColumnDate;
    public TableColumn tableColumnTime;
    public TextField txtReservationsTFFirstLimit;
    public TextField txtReservationsTFSecondLimit;
    public Button btnShowReservationTimeFrame;
    private ReservationService reservationService;


    public void setServices(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            reservations.addAll(reservationService.getAll());
            tableViewReservations.setItems(reservations);
        });
    }

    public void btnShowReservationTimeFrameClick(ActionEvent actionEvent) {
        try {
            String startLimit = txtReservationsTFFirstLimit.getText();
            String endLimit = txtReservationsTFSecondLimit.getText();


            List<Integer> reservationsTimeFrame = reservationService.reservationsTimeFrameReport(startLimit, endLimit);

            reservations.clear();
            for (Integer searchId : reservationsTimeFrame) {
                for (Reservation reservation : reservationService.getAll()) {
                    if (searchId == reservation.getId()) {
                        reservations.add(reservation);
                    }
                }
            }

        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }
}
