package Domain;

import java.util.ArrayList;

public class Movie extends Entity{
    private String title, releaseDate;
    private Double price;
    private Boolean onCinema;
    private Integer points;

    public Movie(Integer id, String title, String releaseDate, Double price, Boolean onCinema) {
        super(id);
        this.title = title;
        this.releaseDate = releaseDate;
        this.price = price;
        this.onCinema = onCinema;
        this.points = (int) (price * 0.1);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + getId() + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", price=" + price +
                ", onCinema=" + onCinema +
                '}';
    }


    public ArrayList<String> getAllFields(){
        ArrayList<String> fields = new ArrayList<>();
        fields.add(this.getTitle());
        fields.add(this.getReleaseDate());
        fields.add(Integer.toString(this.getPoints()));
        fields.add(toString().valueOf(this.getPrice()));
        fields.add(toString().valueOf(this.isOnCinema()));

        return fields;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isOnCinema() {
        return onCinema;
    }

    public void setOnCinema(boolean onCinema) {
        this.onCinema = onCinema;
    }

    public Integer getPoints() {
        return points;
    }
}

