package Tests.Service;

import Domain.IValidator;
import Domain.Movie;
import Domain.MovieValidator;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.MovieService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {
    private IValidator<Movie> movieValidator = new MovieValidator();
    private IRepository<Movie> movieRepository = new InMemoryRepository<>(movieValidator);
    private MovieService movieService = new MovieService(movieRepository);

    @Test
    void addOrUpdate() {
        movieService.addOrUpdate(1, "movie1", "12.12.2019", 15.0, true);
        assertEquals(1, movieService.getAll().size());

        movieService.addOrUpdate(1, "Deadpool1", "18.05.18", 15.0, true);
        assertEquals(1, movieService.getAll().size());

        movieService.addOrUpdate(2, "Deadpool2", "18.05.19", 30.0, true);
        assertEquals(2, movieService.getAll().size());

        movieService.addOrUpdate(3, "Hotel Mumbai", "18.03.19", 25.0, true);
        assertEquals(3, movieService.getAll().size());
    }


    @Test
    void remove() {
        movieService.addOrUpdate(3, "Hotel Mumbai", "18.03.19", 25.0, true);
        assertEquals(1, movieService.getAll().size());

        movieService.remove(3);
        assertEquals(0, movieService.getAll().size());
    }

    @Test
    void getAll() {
        movieService.addOrUpdate(1, "movie1", "12.12.2019", 15.0, true);
        assertEquals(1, movieService.getAll().size());

        movieService.addOrUpdate(2, "Deadpool2", "18.05.19", 30.0, true);
        assertEquals(2, movieService.getAll().size());
    }

    @Test
    void fullTextSearch() {

        movieService.addOrUpdate(1, "Deadpool1", "12.12.2019", 15.0, true);
        movieService.addOrUpdate(2, "Deadpool2", "18.05.19", 30.0, true);


        String[] searchedWords = {"Deadpool2"};
        List<Integer> searchResults = movieService.textSearch(searchedWords);
        assertEquals(2, searchResults.get(0));

    }
}