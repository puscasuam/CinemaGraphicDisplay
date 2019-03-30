package Service;

import CustomException.DataFormatException;
import CustomException.DuplicateCNPException;
import Domain.Client;
import Domain.Reservation;
import Repository.IRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ClientService extends IsSearchable<Client> {
    private IRepository<Client> repository;

    public ClientService(IRepository<Client> repository) {
        this.repository = repository;
    }

    public void addOrUpdate(int id, String CNP, int fidelityPoints, String lastName, String firstName, String dateOfBirth, String dateOfRegistration) throws DuplicateCNPException {

        //we will make sure that the CNP is not already used

        for (Client i : repository.getAll()) {

            if (i.getCNP().equals(CNP)) {
                throw new DuplicateCNPException("The CNP is already used!");
            }
        }

        Client existing = repository.findById(id);
        if (existing != null) {
            // keep unchanged fields as they were
            if (fidelityPoints == 0) {
                fidelityPoints = existing.getFidelityPoints();
            }
            if (lastName.isEmpty()) {
                lastName = existing.getLastName();
            }
            if (firstName.isEmpty()) {
                firstName = existing.getFirstName();
            }
            if (CNP.isEmpty()) {
                CNP = existing.getCNP();
            }
            if (dateOfBirth.isEmpty()) {
                dateOfBirth = existing.getDateOfBirth();
            }
            if (dateOfRegistration.isEmpty()) {
                dateOfRegistration = existing.getDateOfRegistration();
            }
        }
        Client client = new Client(id, CNP, fidelityPoints, lastName, firstName, dateOfBirth, dateOfRegistration);
        repository.insertOrUpdate(client);
    }

    public void remove(int id) {
        repository.remove(id);
    }

    public List<Client> getAll() {
        return repository.getAll();
    }

    public List<Integer> textSearch(String[] words) {
        return super.fullTextSearch(words, repository.getAll());
    }

    /**
     * descending sort for clients by fidelity points
     * @return a list - type client
     */
    public List<Client> sortedByFidelityPoints() {
        List<Client> sortedByFidelityPoints = getAll();

        sortedByFidelityPoints.sort((v1, v2) -> v1.getFidelityPoints() > v2.getFidelityPoints() ? -1 : 0);
        return sortedByFidelityPoints;
    }

    /**
     * get all clients with date of birts between two dates
     * @param startDate -  first limit of time interval
     * @param endDate - second/last limit of time interval
     * @return a list (integer) with client id
     */
    public List<Integer> getClientsBetweenTwoDatesOfBirth(String startDate, String endDate) {
        List<Integer> clientsOnInterval = new ArrayList<>();

        //validation for start date and end date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
        dateFormat.setLenient(false);
        Calendar firstDate = Calendar.getInstance();
        Calendar lastDate = Calendar.getInstance();

        try {
            firstDate.setTime(dateFormat.parse(startDate));
            lastDate.setTime(dateFormat.parse(endDate));
        } catch (ParseException pe) {
            throw new DataFormatException("The date is not in a correct format!");
        }

        for (Client i : repository.getAll()) {

            Calendar currentDate = Calendar.getInstance();
            try {
                currentDate.setTime(dateFormat.parse(i.getDateOfBirth()));
            } catch (ParseException pe) {
                throw new DataFormatException("The date is not in a correct format!");
            }

            if (currentDate.after(firstDate) && (currentDate.before(lastDate))) {
                clientsOnInterval.add(i.getId());
            }
        }

        return clientsOnInterval;
    }

    /**
     * update/increment the fidelity points for a specific list of clients - depending on date of birth
     * @param increment - added value for fidelity points
     * @param startDate - first limit of time interval
     * @param endDate - second/last limit of time interval
     * @return a list (client - type)
     */
    public List<Client> updateFidelityPointDependingOnDateOfBirth(int increment, String startDate, String endDate) {

        List<Integer> clients = getClientsBetweenTwoDatesOfBirth(startDate, endDate);

        for (Integer clientId : clients) {
            Client updated = repository.findById(clientId);
            updated.setFidelityPoints(updated.getFidelityPoints() + increment);

            repository.insertOrUpdate(updated);
        }

        return repository.getAll();
    }
}
