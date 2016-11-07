package com.example.jakubkalinowski.contractfoxandroid;

import java.util.ArrayList;
import java.util.Map;

public class Estimates {
    private String mProjectTitle;
    private String mItemAreaSpecs;
    private String mDetailDescription;
    private ArrayList<String> mCheckedItems;
    private Map<String, String> mFirebaseServerTimeStamp;
    private String mSenderId;
    private String mReceiverId;
    private String mEstimateBody;
    private String mString1TextView;
    private String mString2TextView;

    public Estimates(){}

    public Estimates(String mSenderID, String mReceiverId, Map<String, String> mFirebaseServerTimeStamp,
                     String mProjectTitle, String mItemAreaSpecs, String mDetailDescription,
                     String mString1TextView, String mString2TextView) {
        this.mSenderId = mSenderID;
        this.mReceiverId = mReceiverId;
        this.mFirebaseServerTimeStamp = mFirebaseServerTimeStamp;
        this.mProjectTitle = mProjectTitle;
//        this.mCheckedItems = mCheckedItems;
        this.mItemAreaSpecs = mItemAreaSpecs;
        this.mDetailDescription = mDetailDescription;
        this.mString1TextView = mString1TextView;
        this.mString2TextView = mString2TextView;
    }

    public String getmProjectTitle() {
        return mProjectTitle;
    }

    public void setmProjectTitle(String mProjectTitle) {
        this.mProjectTitle = mProjectTitle;
    }

    public String getmItemAreaSpecs() {
        return mItemAreaSpecs;
    }

    public void setmItemAreaSpecs(String mItemAreaSpecs) {
        this.mItemAreaSpecs = mItemAreaSpecs;
    }

    public String getmDetailDescription() {
        return mDetailDescription;
    }

    public void setmDetailDescription(String mDetailDescription) {
        this.mDetailDescription = mDetailDescription;
    }

    public ArrayList<String> getmCheckedItems() {
        return mCheckedItems;
    }

    public void setmCheckedItems(ArrayList<String> mCheckedItems) {
        this.mCheckedItems = mCheckedItems;
    }

    public Map<String, String> getmFirebaseServerTimeStamp() {
        return mFirebaseServerTimeStamp;
    }

    public void setmFirebaseServerTimeStamp(Map<String, String> mFirebaseServerTimeStamp) {
        this.mFirebaseServerTimeStamp = mFirebaseServerTimeStamp;
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

    public String getmEstimateBody() {
        return mEstimateBody;
    }

    public void setmEstimateBody(String mEstimateBody) {
        this.mEstimateBody = mEstimateBody;
    }

    public String getmString1TextView() {
        return mString1TextView;
    }

    public void setmString1TextView(String mString1TextView) {
        this.mString1TextView = mString1TextView;
    }

    public String getmString2TextView() {
        return mString2TextView;
    }

    public void setmString2TextView(String mString2TextView) {
        this.mString2TextView = mString2TextView;
    }

//    @Override
//    public String toString() {
//        return "Estimates{" +
//                "mCheckedItems=" + mCheckedItems +
//                '}';
//    }

    @Override
    public String toString() {

        return "Project Title: " + mProjectTitle + ".\n "
                + "Project Description: " + mDetailDescription + ".\n"
//                + "Project Area Location: " + Arrays.toString(mCheckedItems) +".\n"
                + "Item/Area Details & Specifications: " + mItemAreaSpecs +".\n "
                + "Will the client provide materials? " + mString1TextView +".\n"
                + "Will the client need assistance with material delivery? " + mString2TextView;
    }
}