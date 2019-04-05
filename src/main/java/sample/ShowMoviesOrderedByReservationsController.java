package sample;

import Domain.MovieFrequenciesOnReservationViewModel;
import Service.ReservationService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class ShowMoviesOrderedByReservationsController {

    public TableView tableViewMovieFrequenciesOnReservations;
    public TableColumn tableColumnTitle;
    public TableColumn tableColumnFrequencies;
    private ReservationService reservationService;

    private ObservableList<MovieFrequenciesOnReservationViewModel> movieFrequencies = FXCollections.observableArrayList();

    public void setServices(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            movieFrequencies.addAll(reservationService.getMovieFrequenciesReport());
            tableViewMovieFrequenciesOnReservations.setItems(movieFrequencies);
        });
    }

}
