import com.example.jakubkalinowski.contractfoxandroid.Address;

/**
 * Created by jakubkalinowski on 9/4/16.
 */
public class Member {

    // Adapt to the FIREBASE syntax

    /*
    TO DO
     */

    private String firstname;
    private String lastname;
    private String telNumber;
    private String email;
    private Boolean contractorOption;
    private Address address;

    public Member(){}

    public Member(String firstname, String lastname, String telNumber, String email, Boolean contractorOption, Address address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.telNumber = telNumber;
        this.email = email;
        this.contractorOption = contractorOption;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getContractorOption (){
        return contractorOption;
    }

    public void setContractorOption(Boolean contractorOption) {
        this.contractorOption = contractorOption;
    }
}
