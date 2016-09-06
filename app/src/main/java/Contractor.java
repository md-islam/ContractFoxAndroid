/**
 * Created by jakubkalinowski on 9/4/16.
 */
public class Contractor extends Member {

    private String briefDescription;
    private String specialization;
    private int availability;

    public Contractor(String firstname, String lastname, String address, String city, String state, int zipcode, String telNumber, String email, Boolean contractorOption, String briefDescription, String specialization, int availability) {
        super(firstname, lastname, address, city, state, zipcode, telNumber, email, contractorOption);
        this.briefDescription = briefDescription;
        this.specialization = specialization;
        this.availability = availability;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
