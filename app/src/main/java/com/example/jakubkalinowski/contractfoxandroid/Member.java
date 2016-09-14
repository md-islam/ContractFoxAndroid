package com.example.jakubkalinowski.contractfoxandroid;

public abstract class Member {

    protected String firstName;
    protected String lastName;
    protected String telNumber;
    protected String email;
    protected Boolean contractorOption;
    protected String profilePicture;
    protected String password;
    protected Address mAddress;

    public Member(){}

    public Member(String firstName, String lastName, String telNumber, String email,
                  Boolean contractorOption, String profilePicture, String password, Address mAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telNumber = telNumber;
        this.email = email;
        this.contractorOption = contractorOption;
        this.profilePicture = profilePicture;
        this.password = password;
        this.mAddress = mAddress;
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
        return mAddress;
    }

    public void setAddress(Address mAddress) {
        this.mAddress = mAddress;
    }
}
