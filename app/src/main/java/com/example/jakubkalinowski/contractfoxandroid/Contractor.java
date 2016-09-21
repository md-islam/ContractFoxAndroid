package com.example.jakubkalinowski.contractfoxandroid;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Contractor extends Member {
    private String briefDescription;
    private String specialization;
    private int availability;
    private ArrayList<String> skillSet;



    private String businessWebsite;


    public Contractor() {
    }

    public Contractor(String firstname, String lastname, String email, String phoneNo,
                      Boolean contractorOption, String briefDescription, String specialization,
                      int availability, Address maddress, ArrayList<String> skillSet,
                      String businessWebsite) {
        super(firstname, lastname, email, phoneNo, contractorOption, maddress);
        this.briefDescription = briefDescription;
        this.specialization = specialization;
        this.availability = availability;
        this.skillSet = skillSet;
        this.businessWebsite = businessWebsite;
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


    //implementing abstract methods

    @Override
    public Boolean getContractorOption() {
        return contractorOption;
    }

    @Override
    public void setContractorOption(Boolean contractorOption) {
        this.contractorOption = contractorOption;
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
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
    public void setAddress(Address address) {
        mAddress = address;
    }

    @Override
    public Address getAddress() {
        return mAddress;
    }


    public ArrayList<String> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(ArrayList<String> skillSet) {
        this.skillSet = skillSet;
    }

    public void addSkill(String skill) {
        skillSet.add(skill);
    }

    public String getBusinessWebsite() {
        return businessWebsite;
    }

    public void setBusinessWebsite(String businessWebsite) {
        this.businessWebsite = businessWebsite;
    }


}