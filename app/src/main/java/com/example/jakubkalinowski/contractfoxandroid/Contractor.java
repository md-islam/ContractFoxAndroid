package com.example.jakubkalinowski.contractfoxandroid;

<<<<<<< HEAD
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by MD on 9/7/2016.
 */
public class Contractor extends Member {
    private String briefDescription;
    private ArrayList<String> skillSet;
    private String businessWebsiteURL;
    private String CompanyName;






    public Contractor() {
    }

    public Contractor(String firstname, String lastname, String email, String phoneNo,
                      Boolean contractorOption, Address mAddress,
                      String briefDescription, ArrayList<String> skillSet,
                      String businessWebsiteURL) {
        super(firstname, lastname, email, phoneNo, contractorOption, mAddress);
        this.briefDescription = briefDescription;
        this.skillSet = skillSet;
        this.businessWebsiteURL = businessWebsiteURL;
    }

=======
import java.util.ArrayList;

/**
 * Created by jakubkalinowski on 9/4/16.
 */
public class Contractor extends Member {

    private String briefDescription;
    private String review; // review text field
    private int availability; // check with android studio given feature
    private int stars; //review stars
    private ArrayList<String> Skills = new ArrayList<String>();
    private ArrayList<String> PictureGallery = new ArrayList<String>();

    public Contractor(){}

    public Contractor(String firstName, String lastName, String telNumber, String email,
                      Boolean contractorOption, String profilePicture, String password, Address address,
                      String briefDescription, String review, int availability, int stars,
                      ArrayList<String> Skills, ArrayList<String> PictureGallery) {
        super(firstName, lastName, telNumber, email,
                contractorOption, profilePicture, password, address);
        this.briefDescription = briefDescription;
        this.review = review;
        this.availability = availability;
        this.stars = stars;
        this.Skills = Skills;
        this.PictureGallery = PictureGallery;
    }

    /*
     * getters and setters for the remaining parameters

     */

>>>>>>> 0c0e93375c6227c9f1ebd451b9946e5860c4b0ca
    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

<<<<<<< HEAD
<<<<<<< 4e7676a248719d22c2cf728f9798e29b8fdd0dec
<<<<<<< HEAD
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
=======
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
>>>>>>> 0c0e93375c6227c9f1ebd451b9946e5860c4b0ca
    }

    public int getAvailability() {
        return availability;
    }
=======
>>>>>>> Welcom Firebase 2.0

=======

>>>>>>> db87086bb8b52d6b115584df6e7f8ff31d598ebb

<<<<<<< HEAD

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

    public String getBusinessWebsiteURL() {
        return businessWebsiteURL;
    }

    public void setBusinessWebsiteURL(String businessWebsiteURL) {
        this.businessWebsiteURL = businessWebsiteURL;
    }


    public String getCompanyName() {
        return CompanyName;
    }
<<<<<<< HEAD

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }


<<<<<<< ea5f8888bf1cd18bc801a1af31d468bbcb214ae4
=======

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }
>>>>>>> db87086bb8b52d6b115584df6e7f8ff31d598ebb


=======
    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
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
>>>>>>> 0c0e93375c6227c9f1ebd451b9946e5860c4b0ca
=======
>>>>>>> changes to registration
}
