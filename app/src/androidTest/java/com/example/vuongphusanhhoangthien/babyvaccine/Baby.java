package com.example.vuongphusanhhoangthien.babyvaccine;

public class Baby {
    private String babyID;
    private String babyName;
    private String babyYob;

    public Baby(String babyID, String babyName, String babyYob) {
        this.babyID = babyID;
        this.babyName = babyName;
        this.babyYob = babyYob;
    }

    public String getBabyID() {
        return babyID;
    }

    public void setBabyID(String babyID) {
        this.babyID = babyID;
    }

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }

    public String getBabyYob() {
        return babyYob;
    }

    public void setBabyYob(String babyYob) {
        this.babyYob = babyYob;
    }
}
