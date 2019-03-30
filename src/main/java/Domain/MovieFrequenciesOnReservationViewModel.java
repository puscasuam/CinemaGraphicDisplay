package Domain;

public class MovieFrequenciesOnReservationViewModel {
    private String title;
    private int frequencies;

    public MovieFrequenciesOnReservationViewModel(String title, int frequencies) {
        this.title = title;
        this.frequencies = frequencies;
    }

    @Override
    public String toString() {
        return "MovieFrequenciesOnReservationViewModel{" +
                "title='" + title + '\'' +
                ", frequencies=" + frequencies +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(int frequencies) {
        this.frequencies = frequencies;
    }
}
