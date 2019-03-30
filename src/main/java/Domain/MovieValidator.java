package Domain;

import CustomException.DataFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MovieValidator implements IValidator<Movie> {

    public void validate(Movie movie) throws DataFormatException {
        if (movie.getId() <= 0) {
            throw new RuntimeException("This is an invalid id number");
        }

        if (movie.getTitle() == null || movie.getTitle().isEmpty()) {
            throw new RuntimeException("Title should be given");
        }

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        format.setLenient(false);

        try {
            format.parse(movie.getReleaseDate());
        } catch (ParseException pe) {
            throw new DataFormatException("The release date is not in a correct format!");
        }

        if (movie.getPrice() <= 0) {
            throw new RuntimeException("Price and must be > 0");
        }
    }
}
