package com.example.jakubkalinowski.contractfoxandroid;

import java.util.ArrayList;

<<<<<<< HEAD
=======
/**
 * Created by jakubkalinowski on 9/4/16.
 */
>>>>>>> parent of 7941c6a... Merge branch 'MD_branch'
public class Contractor extends Member {

    private String briefDescription;
    private String review; // review text field
    private int availability; // check with android studio given feature
    private int stars; //review stars
<<<<<<< HEAD
    private ArrayList<String> SkillSet = new ArrayList<String>();
=======
    private ArrayList<String> Skills = new ArrayList<String>();
>>>>>>> parent of 7941c6a... Merge branch 'MD_branch'
    private ArrayList<String> PictureGallery = new ArrayList<String>();

    public Contractor(){}

    public Contractor(String firstName, String lastName, String telNumber, String email,
                      Boolean contractorOption, String profilePicture, String password, Address address,
                      String briefDescription, String review, int availability, int stars,
<<<<<<< HEAD
                      ArrayList<String> SkillSet, ArrayList<String> PictureGallery) {
=======
                      ArrayList<String> Skills, ArrayList<String> PictureGallery) {
>>>>>>> parent of 7941c6a... Merge branch 'MD_branch'
        super(firstName, lastName, telNumber, email,
                contractorOption, profilePicture, password, address);
        this.briefDescription = briefDescription;
        this.review = review;
        this.availability = availability;
        this.stars = stars;
<<<<<<< HEAD
        this.SkillSet = SkillSet;
=======
        this.Skills = Skills;
>>>>>>> parent of 7941c6a... Merge branch 'MD_branch'
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
<<<<<<< HEAD
=======
    }
>>>>>>> parent of 7941c6a... Merge branch 'MD_branch'

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

<<<<<<< HEAD
    public ArrayList<String> getSkillSet() {
        return SkillSet;
    }

    public void setSkillSet(ArrayList<String> SkillSet) {
        SkillSet = SkillSet;
=======
    public ArrayList<String> getSkills() {
        return Skills;
    }

    public void setSkills(ArrayList<String> skills) {
        Skills = skills;
>>>>>>> parent of 7941c6a... Merge branch 'MD_branch'
    }

    public ArrayList<String> getPictureGallery() {
        return PictureGallery;
    }

<<<<<<< HEAD
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
=======
    public void setPictureGallery(ArrayList<String> pictureGallery) {
        PictureGallery = pictureGallery;
    }
}
>>>>>>> parent of 7941c6a... Merge branch 'MD_branch'
