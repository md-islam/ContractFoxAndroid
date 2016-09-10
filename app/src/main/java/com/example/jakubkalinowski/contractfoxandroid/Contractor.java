package com.example.jakubkalinowski.contractfoxandroid;

import java.util.ArrayList;

public class Contractor extends Member {

    private String briefDescription;
    private String review; // review text field
    private int availability; // check with android studio given feature
    private int stars; //review stars
    private ArrayList<String> SkillSet = new ArrayList<String>();
    private ArrayList<String> PictureGallery = new ArrayList<String>();

    public Contractor(){}

    public Contractor(String firstName, String lastName, String telNumber, String email,
                      Boolean contractorOption, String profilePicture, String password, Address address,
                      String briefDescription, String review, int availability, int stars,
                      ArrayList<String> SkillSet, ArrayList<String> PictureGallery) {
        super(firstName, lastName, telNumber, email,
                contractorOption, profilePicture, password, address);
        this.briefDescription = briefDescription;
        this.review = review;
        this.availability = availability;
        this.stars = stars;
        this.SkillSet = SkillSet;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public ArrayList<String> getSkillSet() {
        return SkillSet;
    }

    public void setSkillSet(ArrayList<String> SkillSet) {
        SkillSet = SkillSet;
    }

    public ArrayList<String> getPictureGallery() {
        return PictureGallery;
    }

    public void setPictureGallery(ArrayList<String> PictureGallery) {
        PictureGallery = PictureGallery;
    }

    //implementing abstract methods

    public Boolean getContractorOption() {
        return contractorOption;
    }

    @Override
    void setContractorOption(Boolean contractorOption) {
        this.contractorOption = contractorOption;
    }

}