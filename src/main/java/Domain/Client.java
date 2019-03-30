package Domain;

import java.util.ArrayList;

public class Client extends Entity {
    private int fidelityPoints;
    private String lastName, firstName, CNP, dateOfBirth, dateOfRegistration;

    public Client(int id, String CNP, int fidelityPoints, String lastName, String firstName, String dateOfBirth, String dateOfRegistration) {
        super(id);
        this.CNP = CNP;
        this.fidelityPoints = fidelityPoints;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfRegistration = dateOfRegistration;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", CNP=" + CNP +
                ", fidelityPoints=" + fidelityPoints +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", dateOfRegistration='" + dateOfRegistration + '\'' +
                '}';
    }

    public ArrayList<String> getAllFields() {
        ArrayList<String> fields = new ArrayList<>();
        fields.add(this.getFirstName());
        fields.add(this.getLastName());
        fields.add(this.getCNP());
        fields.add(this.getDateOfBirth());
        fields.add(this.getDateOfRegistration());

        return fields;
    }

    public String getDateField() {
        return this.getDateOfBirth();
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public int getFidelityPoints() {
        return fidelityPoints;
    }

    public void setFidelityPoints(int fidelityPoints) {
        this.fidelityPoints = fidelityPoints;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }
}
