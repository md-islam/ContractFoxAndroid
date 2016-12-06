package com.example.jakubkalinowski.contractfoxandroid;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public abstract class Member {

    //This is protected because of a bug in Firebase, These variables are accessed in the subclass.
    protected String firstName;
    protected String lastName;
    protected String emailAddress;
    protected String phoneNo;
    protected Boolean contractorOption;
    protected Address mAddress;
    protected int overAllrating ;

    public Member(){}

    public Member(String firstName, String lastName, String emailAddress, String phoneNo,
                  Boolean contractorOption, Address mAddress, int overAllrating){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNo = phoneNo;
        this.contractorOption = contractorOption;
        this.mAddress = mAddress;
        this.overAllrating = 0;
    }

    //These abstract methods are implemented in the subclass.
    public abstract String getFirstName();
    public abstract void setFirstName(String firstName);

    public abstract String getLastName();
    public abstract void setLastName(String lastName);

    public abstract void setEmailAddress(String emailAddress);
    public abstract String getEmailAddress();

    public abstract String getPhoneNo();
    public abstract void setPhoneNo(String phoneNo);

    public abstract Boolean getContractorOption();
    public abstract void setContractorOption(Boolean contractorOption);

    public abstract Address getAddress();
    public abstract void setAddress(Address address);

}

