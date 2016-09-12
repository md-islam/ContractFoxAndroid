package com.example.jakubkalinowski.contractfoxandroid;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by MD on 9/7/2016.
 */
public class Contractor extends Member {
    private String briefDescription;
    private String specialization;
    private int availability;
    private ArrayList<String> skillSet;


    public Contractor(){}
    public Contractor(String firstname, String lastname, String email, String phoneNo,
                      String address, Boolean contractorOption,
                       String briefDescription, String specialization, int availability,
                      Address maddress, ArrayList<String> skillSet) {
        super(firstname, lastname, email, phoneNo, contractorOption, maddress);
        this.briefDescription = briefDescription;
        this.specialization = specialization;
        this.availability = availability;
        this.skillSet = skillSet;
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



    public Boolean getContractorOption() {
        return contractorOption;
    }

    @Override
    void setContractorOption(Boolean contractorOption) {
        this.contractorOption = contractorOption;
    }



//    public Address getAddress() {
//        return mAddress;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }

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


    public void setAddress(Address address) {
        mAddress = address;
    }

    public Address getAddress(){
        return mAddress;
    }


    public ArrayList<String> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(ArrayList<String> skillSet) {
        this.skillSet = skillSet;
    }

    public void addSkill(String skill){
        skillSet.add(skill);
    }




}
