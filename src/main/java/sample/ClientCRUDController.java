package sample;


import Domain.Client;
import Service.ClientService;
import javafx.application.Platform;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    }

    public void btnDeleteClientClick(ActionEvent actionEvent) {
    }
}
