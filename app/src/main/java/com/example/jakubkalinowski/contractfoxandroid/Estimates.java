package com.example.jakubkalinowski.contractfoxandroid;

public class Estimates {
    private String mProjectTitle;
    private String mItemAreaSpecs;
    private String mDetailDescription;

    public Estimates(){}

    public Estimates(String projectTitle, String itemAreaSpecs, String detailDescription) {
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

    @Override
    public String toString() {
        return "Project Title: " + mProjectTitle + "./n "
                + "Project Description: " + mDetailDescription + "./n"
                + "Item/Area Details & Specifications: " + mItemAreaSpecs +"./n ";
    }
}

