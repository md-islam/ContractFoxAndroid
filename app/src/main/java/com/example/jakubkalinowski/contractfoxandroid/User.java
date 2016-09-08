package com.example.jakubkalinowski.contractfoxandroid;

/**
 * Created by MD on 4/22/2016.
 */
public abstract class User {
    protected String firstName;
    protected String lastName;
    protected String emailAddress;
    protected String password;
    protected String address;
    protected Boolean contractor;
    protected Address mAddress;


    public User(){}

    public User(String firstName, String lastName, String emailAddress, String password,
                String address, Boolean contractor, Address mAddress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.address = address;
        this.contractor = contractor;
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

    abstract String getPassword();
    abstract void setPassword(String password);
    abstract Boolean getContractor();
    abstract void setContractor(Boolean contractor);
    //abstract String getAddress();
    //abstract void setAddress(String address);
    abstract void setEmailAddress(String emailAddress);
    abstract String getFirstName();
    abstract void setFirstName(String firstName);
    abstract String getLastName();
    abstract void setLastName(String lastName);
    abstract void setAddress(Address address);
    abstract Address getAddress();


}