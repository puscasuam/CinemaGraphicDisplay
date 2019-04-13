package Tests.Service;

import Domain.IValidator;
import Domain.Movie;
import Domain.MovieValidator;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.MovieService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsSearchableTest {
    private IValidator<Movie> movieValidator = new MovieValidator();
    private IRepository<Movie> movieRepository = new InMemoryRepository<>(movieValidator);
    private MovieService movieService = new MovieService(movieRepository);


    @Test
    void fullTextSearch() {

        movieService.addOrUpdate(1, "Deadpool1", "12.12.2019", 15.0, true);
        movieService.addOrUpdate(2, "Deadpool2", "18.05.19", 30.0, true);


        String[] searchedWords = {"Deadpool2"};
        List<Integer> searchResults = movieService.fullTextSearch(searchedWords, movieService.getAll());
        assertEquals(2, searchResults.get(0));

    }

}