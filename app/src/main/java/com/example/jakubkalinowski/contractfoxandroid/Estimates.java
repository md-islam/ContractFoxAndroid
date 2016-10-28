package com.example.jakubkalinowski.contractfoxandroid;

import java.util.Map;

public class Estimates {
    private String mProjectTitle;
    private String mItemAreaSpecs;
    private String mDetailDescription;
    private Map<String, String> mFirebaseServerTimeStamp;
    private String mSenderId;
    private String mReceiverId;

    public Estimates(){}

    public Estimates(String senderID, String receiverId, Map<String, String> firebaseServerTimeStamp,
                     String projectTitle, String itemAreaSpecs, String detailDescription) {
        mSenderId = senderID;
        mReceiverId = receiverId;
        mFirebaseServerTimeStamp = firebaseServerTimeStamp;
        mProjectTitle = projectTitle;
        mItemAreaSpecs = itemAreaSpecs;
        mDetailDescription = detailDescription;
    }

    //change the abstract methods to public

    public String getProjectTitle() {
        return mProjectTitle;
    }
    public void setProjectTitle(String projectTitle) {
        mProjectTitle = projectTitle;
    }
    public String getItemAreaSpecs() {
        return mItemAreaSpecs;
    }
    public void setItemAreaSpecs(String itemAreaSpecs) {
        mItemAreaSpecs = itemAreaSpecs;
    }
    public String getDetailDescription() {
        return mDetailDescription;
    }
    public void setDetailDescription(String detailDescription) {
        mDetailDescription = detailDescription;
    }

    public String getmSenderId() {
        return mSenderId;
    }

    public void setmSenderId(String mSenderId) {
        this.mSenderId = mSenderId;
    }

    public String getmReceiverId() {
        return mReceiverId;
    }

    public void setmReceiverId(String mReceiverId) {
        this.mReceiverId = mReceiverId;
    }

    public Map<String, String> getmFirebaseServerTimeStamp() {
        return mFirebaseServerTimeStamp;
    }

    public void setmFirebaseServerTimeStamp(Map<String, String> mFirebaseServerTimeStamp) {
        this.mFirebaseServerTimeStamp = mFirebaseServerTimeStamp;
    }

    @Override
    public String toString() {
        return "Project Title: " + mProjectTitle + "./n "
                + "Project Description: " + mDetailDescription + "./n"
                + "Item/Area Details & Specifications: " + mItemAreaSpecs +"./n ";
    }
}