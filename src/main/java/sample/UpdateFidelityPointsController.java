package sample;

import Domain.Client;
import Service.ClientService;
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

public class UpdateFidelityPointsController {
    public TableView tableViewClients;
    public TableColumn tableColumnId;
    public TableColumn tableColumnCNP;
    public TableColumn tableColumnFidelityPoints;
    public TableColumn tableColumnLastName;
    public TableColumn tableColumnFirstName;
    public TableColumn tableColumnDateOfBirth;
    public TableColumn tableColumnDateOfRegistration;
    public Button btnUpdateFidelityPoints;
    public TextField txtClientTFFirstDate;
    public TextField txtClientTFSecondDate;
    public TextField txtClientIncrementOfFidelityPoints;
    private ClientService clientService;


    public void setServices(ClientService clientService) {
        this.clientService = clientService;
    }

    private ObservableList<Client> clients = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            clients.addAll(clientService.getAll());
            tableViewClients.setItems(clients);
        });
    }

    public void btnUpdateFidelityPointsClick(ActionEvent actionEvent) {
        try {
            Integer increment = Integer.parseInt(txtClientIncrementOfFidelityPoints.getText());
            String startDate = txtClientTFFirstDate.getText();
            String endDate = txtClientTFSecondDate.getText();

            List<Client> updateFidelityPointDependingOnDateOfBirth = clientService.updateFidelityPointDependingOnDateOfBirth(increment, startDate, endDate);

            clients.clear();
            for (Client client : updateFidelityPointDependingOnDateOfBirth) {
                clients.addAll(client);
            }

        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }

   }


}
