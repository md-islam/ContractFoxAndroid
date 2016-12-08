package com.example.jakubkalinowski.contractfoxandroid;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Homeowner extends Member {

    double longitude , latitude ;



    public Homeowner(){}


    public Homeowner(String firstname, String lastname, String email, String phoneNo,
                     Boolean contractorOption, Address address , double lat, double longi){
        super(firstname, lastname, email, phoneNo, contractorOption, address );
        this.latitude = lat ;
        this.longitude = longi ;
    }


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }



    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String getPhoneNo() {
        return phoneNo;
    }

    @Override
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public Boolean getContractorOption() {
        return contractorOption;
    }

    @Override
    public void setContractorOption(Boolean contractorOption) {
        this.contractorOption = contractorOption;
    }

    @Override
    public Address getAddress() {
        return mAddress;
    }

    @Override
    public void setAddress(Address address) {
        mAddress = address;
    }
}
