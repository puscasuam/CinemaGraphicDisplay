package Tests.Domain;

import Domain.MovieFrequenciesOnReservationViewModel;
import Domain.Reservation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieFrequenciesOnReservationViewModelTest {

    private MovieFrequenciesOnReservationViewModel viewModel = new MovieFrequenciesOnReservationViewModel("Hotel Mumbai", 3);

    @Test
    void getTitleShouldReturnCorrectTitle() {
        assertEquals("Hotel Mumbai", viewModel.getTitle());
    }

    @Test
    void setTitleShouldSetCorrectly() {
        viewModel.setTitle("Touch me not");
        assertEquals("Touch me not", viewModel.getTitle());
    }

    @Test
    void getFrequenciesShouldReturnCorrectFrequencies() {
        assertEquals(3, viewModel.getFrequencies());
    }

    @Test
    void setFrequenciesShouldSetCorrectly() {
        viewModel.setFrequencies(4);
        assertEquals(4, viewModel.getFrequencies());
    }

}