package com.example.jakubkalinowski.contractfoxandroid;

import java.util.ArrayList;

public class Contractor extends Member {

    private String briefDescription;
    private int availability; // check with android studio given feature
    private ArrayList<String> Skills = new ArrayList<String>();
    private ArrayList<String> PictureGallery = new ArrayList<String>();

    public Contractor(){}

    public Contractor(String firstName, String lastName, String telNumber, String email,
                      Boolean contractorOption, String profilePicture, String password, Address address,
                      String briefDescription, int availability,
                      ArrayList<String> Skills, ArrayList<String> PictureGallery) {
        super(firstName, lastName, telNumber, email,
                contractorOption, profilePicture, password, address);
        this.briefDescription = briefDescription;
        this.availability = availability;
        this.Skills = Skills;
        this.PictureGallery = PictureGallery;
    }

    /*
     * getters and setters for the remaining parameters

     */

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public ArrayList<String> getSkills() {
        return Skills;
    }

    public void setSkills(ArrayList<String> skills) {
        Skills = skills;
    }

    public ArrayList<String> getPictureGallery() {
        return PictureGallery;
    }

    public void setPictureGallery(ArrayList<String> pictureGallery) {
        PictureGallery = pictureGallery;
    }
}
