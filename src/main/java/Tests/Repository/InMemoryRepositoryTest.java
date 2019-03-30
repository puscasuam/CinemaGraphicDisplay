package Tests.Repository;

import Domain.Movie;
import Domain.MovieValidator;
import Repository.IRepository;
import Repository.InMemoryRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRepositoryTest {
    private Movie movieTest = new Movie(1, "Deadpool", "12.12.16", 25.0, false);
    private MovieValidator movieValidator = new MovieValidator();
    private IRepository<Movie> movieIRepositoryitory = new InMemoryRepository<>(movieValidator);

    @Test
    void findByIdWithExistingIdShouldReturnCorrectEntity() {

        movieIRepositoryitory.insertOrUpdate(movieTest);
        assertEquals(movieTest, movieIRepositoryitory.findById(1));
    }

    /**
     * Test only for add
     */
    @Test
    void insertOrUpdateShouldCorrectlyAddAnEntity() {
        movieIRepositoryitory.insertOrUpdate(movieTest);
        List<Movie> movies = movieIRepositoryitory.getAll();

        assertEquals(1, movies.size());
    }

    /**
     * Test only for update
     */
    @Test
    void insertOrUpdateShouldCorrectlyUpdateAnEntity() {
        movieIRepositoryitory.insertOrUpdate(movieTest);
        List<Movie> movies = movieIRepositoryitory.getAll();

        Movie secondMovieTest = new Movie(1, "Deadpool2", "18.05.18", 25.0, false);
        movieIRepositoryitory.insertOrUpdate(secondMovieTest);
        assertEquals(1, movies.size());
        assertEquals("Deadpool2", movies.get(0).getTitle());
    }

    @Test
    void deleteShouldCorrectlyDeleteAnEntity() {
        movieIRepositoryitory.insertOrUpdate(movieTest);
        List<Movie> movies = movieIRepositoryitory.getAll();
        assertEquals(1, movies.size());

        movieIRepositoryitory.remove(movieTest.getId());
        assertEquals(0, movies.size());
    }

    @Test
    void getAllShouldCorrectlyReturnAllEntities(){
        movieIRepositoryitory.insertOrUpdate(movieTest);
        Movie secondMovieTest = new Movie(2, "Deadpool2", "18.05.18", 25.0, false);
        movieIRepositoryitory.insertOrUpdate(secondMovieTest);
        List<Movie> movies = movieIRepositoryitory.getAll();

        assertEquals(2, movies.size());
        assertEquals("Deadpool", movies.get(0).getTitle());
        assertEquals("Deadpool2", movies.get(1).getTitle());
    }

}