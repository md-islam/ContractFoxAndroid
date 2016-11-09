package com.example.jakubkalinowski.contractfoxandroid.Model;

/**
 * Created by MD on 10/29/2016.
 */

public class ContractorSingleDaySchedule {
    public ContractorSingleDaySchedule(ContractorDutySession morningSession,
                                       ContractorDutySession eveningSession,
                                       Boolean availableAllDay, String readableTime,
                                       long timeInMilliseconds) {
        this.morningSession = morningSession;
        this.eveningSession = eveningSession;
        this.availableAllDay = availableAllDay;
        this.readableTime = readableTime;
        this.timeInMilliseconds = timeInMilliseconds;
    }


    //Required empty constructor for Firebase
    public ContractorSingleDaySchedule(){}

    public ContractorDutySession getMorningSession() {
        return morningSession;
    }

    public void setMorningSession(ContractorDutySession morningSession) {
        this.morningSession = morningSession;
    }

    public ContractorDutySession getEveningSession() {
        return eveningSession;
    }

    public void setEveningSession(ContractorDutySession eveningSession) {
        this.eveningSession = eveningSession;
    }

    public Boolean getAvailableAllDay() {
        return availableAllDay;
    }

    public void setAvailableAllDay(Boolean availableAllDay) {
        this.availableAllDay = availableAllDay;
    }

    public String getReadableTime() {
        return readableTime;
    }

    public void setReadableTime(String readableTime) {
        this.readableTime = readableTime;
    }

    ContractorDutySession morningSession;
    ContractorDutySession eveningSession;
    Boolean availableAllDay;
    String readableTime;

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public void setTimeInMilliseconds(long timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }

    @Override
    public String toString() {
        return ""+new Boolean(availableAllDay).toString() + getTimeInMilliseconds() +
                getMorningSession().toString()+getEveningSession().toString();
    }

    long timeInMilliseconds;
}
