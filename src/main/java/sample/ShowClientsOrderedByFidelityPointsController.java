package sample;

import Domain.Client;
import Service.ClientService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class ShowClientsOrderedByFidelityPointsController {

    public TableColumn tableColumnId;
    public TableColumn tableColumnCNP;
    public TableColumn tableColumnFidelityPoints;
    public TableColumn tableColumnLastName;
    public TableColumn tableColumnFirstName;
    public TableColumn tableColumnDateOfBirth;
    public TableColumn tableColumnDateOfRegistration;
    public TableView tableViewOrderedClients;

    private ClientService clientService;

   private ObservableList<Client> clientsOrderedByFidelityPoints = FXCollections.observableArrayList();

    public void setServices(ClientService clientService) {
        this.clientService = clientService;
    }


    @FXML
    private void initialize() {

        Platform.runLater(() -> {
           clientsOrderedByFidelityPoints.addAll(clientService.sortedByFidelityPoints());
            tableViewOrderedClients.setItems(clientsOrderedByFidelityPoints);
        });
    }


}
