package Service;

import CustomException.DataFormatException;
import CustomException.NonExistingEntityException;
import Domain.Client;
import Domain.Movie;
import Domain.MovieFrequenciesOnReservationViewModel;
import Domain.Reservation;
import Repository.IRepository;
import com.sun.javafx.binding.StringFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReservationService extends IsSearchable<Reservation> {

    private IRepository<Movie> movieRepository;
    private IRepository<Client> clientRepository;
    private IRepository<Reservation> reservationRepository;

    public ReservationService(IRepository<Movie> movieRepository, IRepository<Client> clientRepository, IRepository<Reservation> reservationRepository) {
        this.movieRepository = movieRepository;
        this.clientRepository = clientRepository;
        this.reservationRepository = reservationRepository;
    }

    /**
     * Add a new reservation or update an existing one
     * Update de number of fidelitypoints if the movie or the client is update
     *
     * @param id
     * @param movieId
     * @param idClient
     * @param date
     * @param time
     * @return
     */
    public Reservation addOrUpdate(int id, int movieId, int idClient, String date, String time) throws NonExistingEntityException {

        Reservation existingReservation = reservationRepository.findById(id);

        if (existingReservation != null) {

            Client oldExistingClient = clientRepository.findById(existingReservation.getIdClient());
            Movie oldExistingMovie = movieRepository.findById(existingReservation.getMovieId());
            boolean needsUpdate = true;

            // keep unchanged fields as they were
            if (movieId == 0) {
                movieId = existingReservation.getMovieId();
            } else {
                needsUpdate = false;
                if (oldExistingClient != null) {
                    oldExistingClient.setFidelityPoints(oldExistingClient.getFidelityPoints() - oldExistingMovie.getPoints());
                }
            }

            if (idClient == 0) {
                idClient = existingReservation.getIdClient();
            } else {
                if (needsUpdate == true) {
                    if (oldExistingClient != null) {
                        oldExistingClient.setFidelityPoints(oldExistingClient.getFidelityPoints() - oldExistingMovie.getPoints());
                    }
                }
            }

            if (date.isEmpty()) {
                date = existingReservation.getDate();
            }
            if (time.isEmpty()) {
                time = existingReservation.getTime();
            }
        }

        Movie existingMovie = movieRepository.findById(movieId);
        if (existingMovie == null) {
            throw new NonExistingEntityException("There is no movie with the given id!");
        }

        if (existingMovie.isOnCinema() == false) {
            throw new NonExistingEntityException("The movie is not on cinema");
        }

        Client existingClient = clientRepository.findById(idClient);
        if (existingClient != null) {
            existingClient.setFidelityPoints(existingClient.getFidelityPoints() + existingMovie.getPoints());
        }

        Reservation reservation = new Reservation(id, movieId, idClient, date, time);
        reservationRepository.insertOrUpdate(reservation);

        return reservation;
    }


    /**
     * remove a reservation by id reservation
     * update the number of fidelity points
     *
     * @param id to be removed
     */
    public void remove(Integer id) {
        Reservation existingReservation = reservationRepository.findById(id);

        if (existingReservation != null) {
            if (existingReservation.getIdClient() != 0) {
                Client existingClient = clientRepository.findById(existingReservation.getIdClient());
                Movie existingMovie = movieRepository.findById(existingReservation.getMovieId());

                if (existingClient != null) {
                    existingClient.setFidelityPoints(existingClient.getFidelityPoints() - existingMovie.getPoints());
                }
            }
        }

        reservationRepository.remove(id);
    }

    /**
     * get all movies and all fields
     *
     * @return a list of reservations with all fields
     */
    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    /**
     * get all reservation for a specific time frame - it doesn't matter the booking day
     *
     * @param startTime - first limit of time interval
     * @param endTime   - second/last limit of time interval
     * @return a list of reservationid
     */
    public List<Integer> reservationsTimeFrameReport(String startTime, String endTime) {

        //validation for start time and end time
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
        formatTime.setLenient(false);

        try {
            formatTime.parse(startTime);
            formatTime.parse(endTime);
        } catch (ParseException pe) {
            throw new DataFormatException("The time is not in a correct format!");
        }

        List<Integer> reservationsTimeFrame = new ArrayList<>();
        String[] hoursAndMinStartTime = startTime.split(":");
        String[] hoursAndMinEndTime = endTime.split(":");


        for (Reservation i : reservationRepository.getAll()) {
            String[] hoursAndMinCurrentTime = i.getTime().split(":");
            boolean isInTimeInterval = false;

            if (Integer.parseInt(hoursAndMinCurrentTime[0]) > Integer.parseInt(hoursAndMinStartTime[0]) &&
                    Integer.parseInt(hoursAndMinCurrentTime[0]) < Integer.parseInt(hoursAndMinEndTime[0])) {
                isInTimeInterval = true;
            }

            if (Integer.parseInt(hoursAndMinCurrentTime[0]) == Integer.parseInt(hoursAndMinStartTime[0])) {
                if (Integer.parseInt(hoursAndMinCurrentTime[1]) >= Integer.parseInt(hoursAndMinStartTime[1])) {
                    isInTimeInterval = true;
                }
            }

            if (Integer.parseInt(hoursAndMinCurrentTime[0]) == Integer.parseInt(hoursAndMinEndTime[0])) {
                if (Integer.parseInt(hoursAndMinCurrentTime[1]) <= Integer.parseInt(hoursAndMinEndTime[1])) {
                    isInTimeInterval = true;
                }
            }

            if (isInTimeInterval == true) {
                reservationsTimeFrame.add(i.getId());
            }
        }
        return reservationsTimeFrame;
    }

    /**
     * sort movies by booking frequency
     *
     * @return a list - MovieFrequenciesOnReservationViewModel (attributes - title and frequencies)
     */
    public List<MovieFrequenciesOnReservationViewModel> getMovieFrequenciesReport() {

        List<MovieFrequenciesOnReservationViewModel> movieFrequenciesByTitle = new ArrayList<>();
        Map<Integer, Integer> countFrequencies = countFrequencies();

        for (Movie movie : movieRepository.getAll()) {
            int movieID = movie.getId();
            if (countFrequencies.containsKey(movieID)) {
                MovieFrequenciesOnReservationViewModel movieFrequency = new MovieFrequenciesOnReservationViewModel(movie.getTitle(), countFrequencies.get(movieID));
                movieFrequenciesByTitle.add(movieFrequency);
            }
        }

        movieFrequenciesByTitle.sort((v1, v2) -> v1.getFrequencies() > v2.getFrequencies() ? -1 : 0);

        return movieFrequenciesByTitle;

    }

    /**
     * counts for how many times was booked a particular movie
     *
     * @return a map - key - movieID, object - frequencies
     */
    private Map<Integer, Integer> countFrequencies() {
        Map<Integer, Integer> frequencies = new HashMap<>();

        for (Reservation reservation : reservationRepository.getAll()) {
            int firstAppearance = 1;
            int movieId = reservation.getMovieId();

            if (!frequencies.containsKey(movieId)) {
                frequencies.put(movieId, firstAppearance);
            } else {
                frequencies.replace(movieId, frequencies.get(movieId) + 1);
            }
        }

        return frequencies;
    }

    /**
     * get all reservations between two dates
     * @param startDate - first limit of time interval
     * @param endDate   - second/last limit of time interval
     * @return
     */
    public List<Integer> getReservationsBetweenTwoDates(String startDate, String endDate) {
        List<Integer> reservationsOnInterval = new ArrayList<>();

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


        for (Reservation i : reservationRepository.getAll()) {

            Calendar currentDate = Calendar.getInstance();
            try {
                currentDate.setTime(dateFormat.parse(i.getDate()));
            } catch (ParseException pe) {
                throw new DataFormatException("The date is not in a correct format!");
            }

            if (currentDate.after(firstDate) && (currentDate.before(lastDate))) {
                reservationsOnInterval.add(i.getId());
            }
        }

        return reservationsOnInterval;
    }

    /**
     * delete all reservations between two dates
     * @param startDate - first limit of time interval
     * @param endDate   - second/last limit of time interval
     * @return
     */
    public List<Reservation> deleteReservationsBetweenTwoDates(String startDate, String endDate) {

        List<Integer> reservations = getReservationsBetweenTwoDates(startDate, endDate);
        for (Integer reservationId : reservations) {
            reservationRepository.remove(reservationId);
        }

        return reservationRepository.getAll();
    }


}



