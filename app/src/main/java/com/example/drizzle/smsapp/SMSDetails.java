package com.example.drizzle.smsapp;

public class SMSDetails {
    String phoneNumber;
    String message;
    String time;

    public SMSDetails() {
    }
    //    public SMSDetails(String phoneNumber, String message, String time) {
//        this.phoneNumber = phoneNumber;
//        this.message = message;
//        this.time = time;
//    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
