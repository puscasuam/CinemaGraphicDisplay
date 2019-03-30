package Domain;

import CustomException.DataFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientValidator implements IValidator<Client> {
    public void validate(Client client) throws DataFormatException {
        if (client.getId() <= 0) {
            throw new RuntimeException("This is an invalid id number");
        }

        if (client.getFirstName() == null || client.getFirstName().isEmpty()) {
            throw new RuntimeException("First name should be given");
        }

        if (client.getLastName() == null || client.getLastName().isEmpty()) {
            throw new RuntimeException("Last name should be given");
        }

        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        format.setLenient(false);

        try {
            format.parse(client.getDateOfBirth());

        } catch (ParseException pe) {
            throw new DataFormatException("The release date is not in a correct format or is not a valid date!");
        }

        try {
            format.parse(client.getDateOfRegistration());
        } catch (ParseException pe) {
            throw new DataFormatException("The registration date is not in a correct format!");
        }

        if (client.getCNP().length() != 13) {
            throw new RuntimeException("The CNP must have 13 characters!");
        }

        if (client.getCNP().charAt(0) < '1' || client.getCNP().charAt(0) > '8') {
            throw new RuntimeException("CNP not valid!");
        }

        if (client.getFidelityPoints() < 0) {
            throw new RuntimeException("Fidelity points must be minimum 0");
        }


    }

}
