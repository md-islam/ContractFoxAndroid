package com.example.jakubkalinowski.contractfoxandroid;

public class Member {

    protected String firstName;
    protected String lastName;
    protected String telNumber;
    protected String email;
    protected Boolean contractorOption;
    protected String profilePicture;
    protected String password;

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
        this.address = address;
    }
}
