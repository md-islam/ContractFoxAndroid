package com.example.jakubkalinowski.contractfoxandroid;

/**
<<<<<<< HEAD
 * Created by MD on 4/22/2016.
 */
public abstract class Member {


    //This is protected because of a bug in Firebase, These variables are accessed in the subclass.
    protected String firstName;
    protected String lastName;
    protected String emailAddress;
    protected String phoneNo;
    protected Boolean contractorOption;
    protected Address mAddress;

    public Member(){}

    public Member(String firstName, String lastName, String emailAddress, String phoneNo,
                Boolean contractorOption, Address mAddress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNo = phoneNo;
        this.contractorOption = contractorOption;
        this.mAddress = mAddress;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Boolean getContractor() {
//        return contractor;
//    }
//
//    public void setContractor(Boolean contractor) {
//        this.contractor = contractor;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getEmailAddress() {
//        return emailAddress;
//    }
//
//    public void setEmailAddress(String emailAddress) {
//        this.emailAddress = emailAddress;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }


    //These abstract methods are implemented in the subclass.
    public abstract String getFirstName();
    public abstract void setFirstName(String firstName);

    public abstract String getLastName();
    public abstract void setLastName(String lastName);

    public abstract void setEmailAddress(String emailAddress);
    public abstract String getEmailAddress();

    public abstract String getPhoneNo();
    public abstract void setPhoneNo(String phoneNo);

    public abstract Boolean getContractorOption();
     abstract void setContractorOption(Boolean contractorOption);

    public abstract Address getAddress();
    public abstract void setAddress(Address address);





}
=======
 * Created by jakubkalinowski on 9/4/16.
 */
public class Member {

    /*
     TO DO:
     Adapt to the FIREBASE syntax
      */

    private String firstName;
    private String lastName;
    private String telNumber;
    private String email;
    private Boolean contractorOption;
    private String profilePicture;
    private String password;

    Address address = new Address();

    public Member(){}

    public Member(String firstName, String lastName, String telNumber, String email,
                  Boolean contractorOption, String profilePicture, String password, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telNumber = telNumber;
        this.email = email;
        this.contractorOption = contractorOption;
        this.profilePicture = profilePicture;
        this.password = password;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Boolean getContractorOption() {
        return contractorOption;
    }

    public void setContractorOption(Boolean contractorOption) {
        this.contractorOption = contractorOption;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
>>>>>>> 0c0e93375c6227c9f1ebd451b9946e5860c4b0ca
