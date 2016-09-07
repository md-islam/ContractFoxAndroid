import com.example.jakubkalinowski.contractfoxandroid.Address;
import java.util.ArrayList;

/**
 * Created by jakubkalinowski on 9/4/16.
 */
public class Contractor extends Member {


    /*
    TO Do
    Add ratings (points)
    Add reviews (Script)
    add profile picture (String)
    add before/after pictures (Array of strings)
     */

    private String briefDescription;
    // private String specialization; // STRING OR ARRAYLIST DEPENDABLE ON DB FORMAT
    private ArrayList<String> Skills = new ArrayList<>();
    private int availability; // check with android studio given feature

    //modify constructor !!!!!!!!!!!!
    public Contractor(String firstname, String lastname, String telNumber, String email,
                      Boolean contractorOption, Address address, String briefDescription,
                      int availability) {
        super(firstname, lastname, telNumber, email,
                contractorOption, address);
        this.briefDescription = briefDescription;
        this.availability = availability;
    }

    /*
     * getters and setters for the remaining parameters

     */

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public ArrayList<String> getSkills() {
        return Skills;
    }

    public void setSkills(ArrayList<String> skills) {
        Skills = skills;
    }
}
