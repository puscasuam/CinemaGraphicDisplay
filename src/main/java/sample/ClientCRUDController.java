package sample;


import Domain.Client;
import Domain.Movie;
import Service.ClientService;
import javafx.application.Platform;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class ClientCRUDController {

    public TableView tableViewClients;
    public TableColumn tableColumnId;
    public TableColumn tableColumnCNP;
    public TableColumn tableColumnFidelityPoints;
    public TableColumn tableColumnLastName;
    public TableColumn tableColumnFirstName;
    public TableColumn tableColumnDateOfBirth;
    public TableColumn tableColumnDateOfRegistration;
    public TextField txtClientID;
    public TextField txtClientCNP;
    public TextField txtClientFidelityPoints;
    public TextField txtClientLastName;
    public TextField txtClientFirstName;
    public TextField txtClientDateOfBirth;
    public TextField txtClientDateOfRegistration;
    public Button btnAddClient;
    public Button btnDeleteClient;


    private ClientService clientService;

    private ObservableList<Client> clients = FXCollections.observableArrayList();

    public void setService(ClientService clientService) {
        this.clientService = clientService;
    }


    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            clients.addAll(clientService.getAll());
            tableViewClients.setItems(clients);
        });
    }


    public void btnAddClientClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information dialog");
        alert.setHeaderText(null);
        alert.setContentText("Client has been added or updated");
        alert.showAndWait();

        try {
            Integer id = Integer.parseInt(txtClientID.getText());
            String CNP = txtClientCNP.getText();
            Integer fidelityPoints = Integer.parseInt(txtClientFidelityPoints.getText());
            String lastName = txtClientLastName.getText();
            String firstName = txtClientFirstName.getText();
            String dateOfBirth = txtClientDateOfBirth.getText();
            String dateOfRegistration = txtClientDateOfRegistration.getText();

            clientService.addOrUpdate(id, CNP, fidelityPoints, lastName, firstName, dateOfBirth, dateOfRegistration);

            clients.clear();
            clients.addAll(clientService.getAll());
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void btnDeleteClientClick(ActionEvent actionEvent) {
        Movie selectedClient = (Movie) tableViewClients.getSelectionModel().getSelectedItem();

        if (selectedClient != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                try {
                    clientService.remove(selectedClient.getId());
                    clients.clear();
                    clients.addAll(clientService.getAll());
                } catch (RuntimeException rex) {
                    Common.showValidationError(rex.getMessage());
                }
            }
        }
    }
}
