package com.example.jakubkalinowski.contractfoxandroid;

<<<<<<< HEAD
<<<<<<< HEAD
=======


>>>>>>> 0c0e93375c6227c9f1ebd451b9946e5860c4b0ca
=======

>>>>>>> db87086bb8b52d6b115584df6e7f8ff31d598ebb
/**
 * Created by MD on 9/5/2016.
 */
public class Address {
    private String mStreetAddress;
    private String mCity;
    private String mState;
    private String mZipCode;
    private String mUnit_Apt_no;


    public Address(){}

    public Address(String streetAddress, String city, String state, String zipCode, String unit_Apt_no) {
        mStreetAddress = streetAddress;
        mCity = city;
        mState = state;
        mZipCode = zipCode;
        mUnit_Apt_no = unit_Apt_no;
    }
<<<<<<< HEAD
<<<<<<< 11e7ced451df4896b3f2eb8a7e811e590ac718a0
<<<<<<< HEAD
=======

    //change the abstract methods to public

>>>>>>> New Registration experience
    public String getStreetAddress() {
        return mStreetAddress;
    }
    public void setStreetAddress(String streetAddress) {
        mStreetAddress = streetAddress;
    }
    public String getCity() {
        return mCity;
    }
    public void setCity(String city) {
        mCity = city;
    }
    public String getState() {
        return mState;
    }
    public void setState(String state) {
        mState = state;
    }
    public String getZipCode() {
        return mZipCode;
    }
    public void setZipCode(String zipCode) {
        mZipCode = zipCode;
    }
    public String getUnit_Apt_no() {
        return mUnit_Apt_no;
    }
    public void setUnit_Apt_no(String unit_Apt_no) {
        mUnit_Apt_no = unit_Apt_no;
    }

    @Override
    public String toString() {
        return mStreetAddress+", "+mUnit_Apt_no+", "+
                mCity+", "+mState+", "+mZipCode;
    }
}
=======

=======

    //change the abstract methods to public

>>>>>>> db87086bb8b52d6b115584df6e7f8ff31d598ebb
    public String getStreetAddress() {
        return mStreetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        mStreetAddress = streetAddress;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getZipCode() {
        return mZipCode;
    }

    public void setZipCode(String zipCode) {
        mZipCode = zipCode;
    }

    public String getUnit_Apt_no() {
        return mUnit_Apt_no;
    }

    public void setUnit_Apt_no(String unit_Apt_no) {
        mUnit_Apt_no = unit_Apt_no;
    }

    @Override
    public String toString() {
        return mStreetAddress+", "+mUnit_Apt_no+", "+
                mCity+", "+mState+", "+mZipCode;
    }
}
<<<<<<< HEAD
>>>>>>> 0c0e93375c6227c9f1ebd451b9946e5860c4b0ca
=======

>>>>>>> db87086bb8b52d6b115584df6e7f8ff31d598ebb
