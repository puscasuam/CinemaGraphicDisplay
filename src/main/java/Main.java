import Domain.*;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.ClientService;
import Service.MovieService;
import Service.ReservationService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.MainController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = fxmlLoader.load();

        IValidator<Movie> movieValidator = new MovieValidator();
        IValidator<Client> clientValidator = new ClientValidator();
        IValidator<Reservation> reservationValidator = new ReservationValidator();

        IRepository<Movie> movieRepository = new InMemoryRepository<>(movieValidator);
        IRepository<Client> clientRepository = new InMemoryRepository<>(clientValidator);
        IRepository<Reservation> reservationRepository = new InMemoryRepository<>(reservationValidator);

        MovieService movieService = new MovieService(movieRepository);
        movieService.addOrUpdate(1, "Batman", "12.12.2017", 20.0, true);
        movieService.addOrUpdate(2, "Superman", "12.12.2018", 40.0, true);
        movieService.addOrUpdate(3, "Robin Hood", "15.07.2018", 15.0, true);


        ClientService clientService = new ClientService(clientRepository);
        clientService.addOrUpdate(1,"1234567891234",10,"Popescu", "Paul", "12.07.1989", "12.12.2019");
        clientService.addOrUpdate(2,"1234567892234",10,"Ionescu", "Ion", "02.06.1987", "12.12.2019");
        clientService.addOrUpdate(3,"1234567877234",0,"Vasilescu", "Vasile", "07.02.1997", "12.12.2019");

        ReservationService reservationService = new ReservationService(movieRepository, clientRepository, reservationRepository);
        reservationService.addOrUpdate(1,1,-1, "12.12.2018", "12:45");
        reservationService.addOrUpdate(2,1,1, "12.01.2019", "14:25");
        MainController mainController = fxmlLoader.getController();
        mainController.setServices(movieService, clientService, reservationService);

        primaryStage.setTitle("Movie manager");
        primaryStage.setScene(new Scene(root, 500, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
