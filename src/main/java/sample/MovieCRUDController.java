package sample;

import Domain.Movie;
import Service.MovieService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieCRUDController {

    public TextField txtMovieID;
    public TextField txtMovieTitle;
    public TextField txtMovieReleaseDate;
    public TextField txtMoviePrice;
    public CheckBox chkOnCinema;
    public TableView tableViewMovies;
    public TableColumn tableColumnId;
    public TableColumn tableColumnTitle;
    public TableColumn tableColumnReleaseDate;
    public TableColumn tableColumnPrice;
    public TableColumn tableColumnOnCinema;
    public Button btnAddMovie;
    public Button btnDeleteMovie;


    private MovieService movieService;

    private ObservableList<Movie> movies = FXCollections.observableArrayList();

    public void setService(MovieService movieService) {
        this.movieService = movieService;
    }

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            movies.addAll(movieService.getAll());
            tableViewMovies.setItems(movies);
        });
    }

    public void btnAddMovieClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information dialog");
        alert.setHeaderText(null);
        alert.setContentText("Movie has been added or updated");
        alert.showAndWait();

        try {
            Integer id = Integer.parseInt(txtMovieID.getText());
            String title = txtMovieTitle.getText();
            String releaseDate = txtMovieReleaseDate.getText();
            double price = Double.parseDouble(txtMoviePrice.getText());
            boolean onCinema = chkOnCinema.isSelected();

            movieService.addOrUpdate(id, title, releaseDate, price, onCinema);

            movies.clear();
            movies.addAll(movieService.getAll());
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void btnDeleteMovieClick(ActionEvent actionEvent) {
        Movie selectedMovie = (Movie) tableViewMovies.getSelectionModel().getSelectedItem();

        if (selectedMovie != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                try {
                    movieService.remove(selectedMovie.getId());
                    movies.clear();
                    movies.addAll(movieService.getAll());
                } catch (RuntimeException rex) {
                    Common.showValidationError(rex.getMessage());
                }
            }
        }
    }


}
