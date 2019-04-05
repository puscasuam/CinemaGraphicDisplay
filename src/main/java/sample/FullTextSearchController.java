package sample;

import Domain.Client;
import Domain.Movie;
import Service.ClientService;
import Service.MovieService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class FullTextSearchController {
    public TableView tableViewMovies;
    public TableColumn tableMovieColumnId;
    public TableColumn tableMovieColumnTitle;
    public TableColumn tableMovieColumnReleaseDate;
    public TableColumn tableMovieColumnPrice;
    public TableColumn tableMovieColumnOnCinema;
    public TableView tableViewClients;
    public TableColumn tableClientColumnId;
    public TableColumn tableClientColumnCNP;
    public TableColumn tableClientColumnFidelityPoints;
    public TableColumn tableClientColumnLastName;
    public TableColumn tableClientColumnFirstName;
    public TableColumn tableClientColumnDateOfBirth;
    public TableColumn tableClientColumnDateOfRegistration;
    public TextField txtFullTextSearch;
    public Button btFullTextSearch;


    private MovieService movieService;
    private ClientService clientService;

    private ObservableList<Client> clients = FXCollections.observableArrayList();
    private ObservableList<Movie> movies = FXCollections.observableArrayList();

    public void setServices(MovieService movieService, ClientService clientService) {
        this.movieService = movieService;
        this.clientService = clientService;
    }


    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            clients.addAll(clientService.getAll());
            tableViewClients.setItems(clients);

            movies.addAll(movieService.getAll());
            tableViewMovies.setItems(movies);

        });
    }

    public void btnFullTextSearchClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information dialog");
        alert.setHeaderText(null);
        alert.setContentText("Text has been searched");
        alert.showAndWait();

        try {
            String searchedText = txtFullTextSearch.getText();
            String[] searchedWord = searchedText.split("\\s");

            List<Integer> searchInClients = new ArrayList<>();
            List<Integer> searchInMovies = new ArrayList<>();

            searchInClients = clientService.textSearch(searchedWord);
            searchInMovies = movieService.textSearch(searchedWord);

            clients.clear();
            for (Integer searchId : searchInClients) {
                for (Client client : clientService.getAll()) {
                    if (searchId == client.getId()) {
                        clients.add(client);
                    }
                }
            }

            movies.clear();
            for (Integer searchId : searchInMovies) {
                for (Movie movie : movieService.getAll()) {
                    if (searchId == movie.getId()) {
                        movies.add(movie);
                    }
                }
            }


        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }


    }
}
