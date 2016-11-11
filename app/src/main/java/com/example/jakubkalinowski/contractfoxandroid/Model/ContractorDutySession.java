package com.example.jakubkalinowski.contractfoxandroid.Model;

/**
 * Created by MD on 10/29/2016.
 */

public class ContractorDutySession {
    String description;
    String appointmentSession;
    long appointmentStartTimeMilliseconds;
    long appointmentEndTimeMilliseconds;
    String readableAppointmentStartTime;
    String readableAppointmentEndTime;
    Boolean availableDuringSession;
    String readableSessionDate;


    public ContractorDutySession(String description, String appointmentSession,
                                 long appointmentStartTimeMilliseconds, long appointmentEndTimeMilliseconds,
                                 String readableAppointmentStartTime,
                                 String readableAppointmentEndTime,
                                 Boolean availableDuringSession, String readableSessionDate) {
        this.description = description;
        this.appointmentSession = appointmentSession;
        this.appointmentStartTimeMilliseconds = appointmentStartTimeMilliseconds;
        this.appointmentEndTimeMilliseconds = appointmentEndTimeMilliseconds;
        this.readableAppointmentStartTime = readableAppointmentStartTime;
        this.readableAppointmentEndTime = readableAppointmentEndTime;
        this.availableDuringSession = availableDuringSession;
        this.readableSessionDate = readableSessionDate;
    }

    //required empty constructor for DataSnapshot.getValue calls
    public ContractorDutySession() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppointmentSession() {
        return appointmentSession;
    }

    public void setAppointmentSession(String appointmentSession) {
        this.appointmentSession = appointmentSession;
    }

    public long getAppointmentStartTimeMilliseconds() {
        return appointmentStartTimeMilliseconds;
    }

    public void setAppointmentStartTimeMilliseconds(long appointmentStartTimeMilliseconds) {
        this.appointmentStartTimeMilliseconds = appointmentStartTimeMilliseconds;
    }

    public long getAppointmentEndTimeMilliseconds() {
        return appointmentEndTimeMilliseconds;
    }

    public void setAppointmentEndTimeMilliseconds(long appointmentEndTimeMilliseconds) {
        this.appointmentEndTimeMilliseconds = appointmentEndTimeMilliseconds;
    }

    public Boolean getAvailableDuringSession() {
        return availableDuringSession;
    }

    public void setAvailableDuringSession(Boolean availableDuringSession) {
        this.availableDuringSession = availableDuringSession;
    }

    public String getReadableSessionDate() {
        return readableSessionDate;
    }

    public void setReadableSessionDate(String readableSessionDate) {
        this.readableSessionDate = readableSessionDate;
    }

    public String getReadableAppointmentStartTime() {
        return readableAppointmentStartTime;
    }

    public void setReadableAppointmentStartTime(String readableAppointmentStartTime) {
        this.readableAppointmentStartTime = readableAppointmentStartTime;
    }

    public String getReadableAppointmentEndTime() {
        return readableAppointmentEndTime;
    }

    public void setReadableAppointmentEndTime(String readableAppointmentEndTime) {
        this.readableAppointmentEndTime = readableAppointmentEndTime;
    }

    @Override
    public String toString() {
        return ""+getReadableAppointmentStartTime()+getReadableAppointmentEndTime()+getAppointmentSession()+getDescription();
    }
}
