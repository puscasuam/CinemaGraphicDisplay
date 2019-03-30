package Domain;

import CustomException.DataFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservationValidator implements IValidator<Reservation>{
    public void validate(Reservation reservation) throws DataFormatException {
        if (reservation.getId() <= 0) {
            throw new RuntimeException("This is an invalid id number");
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
        dateFormat.setLenient(false);

        try {
            Date date = dateFormat.parse(reservation.getDate());
        } catch (ParseException pe) {
            throw new DataFormatException("The date is not in a correct format!");
        }

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeFormat.setLenient(false);
        try {
            Date time = timeFormat.parse(reservation.getTime());

        } catch (ParseException pe) {
            throw new DataFormatException("The time is not in a correct format!");
        }

    }
}
