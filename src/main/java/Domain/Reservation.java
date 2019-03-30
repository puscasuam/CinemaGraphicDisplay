package Domain;

import java.util.ArrayList;

public class Reservation extends Entity {
    private int movieId, idClient;
    private String date, time;


    public Reservation(int id, int movieId, int idClient, String date, String time) {
        super(id);
        this.movieId = movieId;
        this.idClient = idClient;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + getId() +
                ", movieId=" + movieId +
                ", idClient=" + idClient +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public ArrayList<String> getAllFields(){
        ArrayList<String> fields = new ArrayList<>();
        fields.add(this.getDate());
        fields.add(this.getTime());
        fields.add(Integer.toString(this.getIdClient()));
        fields.add(Integer.toString(this.getMovieId()));

        return fields;
    }


    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
