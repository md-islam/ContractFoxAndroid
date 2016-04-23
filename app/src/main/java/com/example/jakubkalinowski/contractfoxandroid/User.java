package com.example.jakubkalinowski.contractfoxandroid;

/**
 * Created by MD on 4/22/2016.
 */
public class User {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String address;
    private Boolean contractor;


    public User(){}

    public User(String firstName, String lastName, String emailAddress, String password,
                String address, Boolean contractor){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.address = address;
        this.contractor = contractor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getContractor() {
        return contractor;
    }

    public void setContractor(Boolean contractor) {
        this.contractor = contractor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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

}