package com.example.jakubkalinowski.contractfoxandroid;

<<<<<<< HEAD
public class Member {

    protected String firstName;
    protected String lastName;
    protected String telNumber;
    protected String email;
    protected Boolean contractorOption;
    protected String profilePicture;
    protected String password;
=======
/**
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
>>>>>>> parent of 7941c6a... Merge branch 'MD_branch'

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

<<<<<<< HEAD
    abstract String getFirstName() {
        return firstName;
    }

    abstract void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    abstract String getLastName() {
        return lastName;
    }

    abstract void setLastName(String lastName) {
        this.lastName = lastName;
    }

    abstract String getTelNumber() {
        return telNumber;
    }

    abstract void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    abstract String getEmail() {
        return email;
    }

    abstract void setEmail(String email) {
        this.email = email;
    }

    abstract Boolean getContractorOption() {
        return contractorOption;
    }

    abstract void setContractorOption(Boolean contractorOption) {
        this.contractorOption = contractorOption;
    }

    abstract String getProfilePicture() {
        return profilePicture;
    }

    abstract void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    abstract String getPassword() {
        return password;
    }

    abstract void setPassword(String password) {
        this.password = password;
    }

    abstract Address getAddress() {
        return address;
    }

    abstract void setAddress(Address address) {
=======
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
>>>>>>> parent of 7941c6a... Merge branch 'MD_branch'
        this.address = address;
    }
}
