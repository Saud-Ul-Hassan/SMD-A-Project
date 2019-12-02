package com.example.travelicious;

public class TripClass {
    String name;
    String Description;
    String charges;
    String profilePic1;
    String profilePic2;
    String profilePic3;
    public TripClass() {
    }

    public TripClass(String name, String desc, String charges, String profilePic1, String profilePic2, String profilePic3) {
        this.name = name;
        this.Description = desc;
        this.charges = charges;
        this.profilePic1 = profilePic1;
        this.profilePic2 = profilePic2;
        this.profilePic3 = profilePic3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return Description;
    }

    public void setDesc(String desc) {
        this.Description = desc;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public String getProfilePic1() {
        return profilePic1;
    }

    public void setProfilePic1(String profilePic1) {
        this.profilePic1 = profilePic1;
    }

    public String getProfilePic2() {
        return profilePic2;
    }

    public void setProfilePic2(String profilePic2) {
        this.profilePic2 = profilePic2;
    }

    public String getProfilePic3() {
        return profilePic3;
    }

    public void setProfilePic3(String profilePic3) {
        this.profilePic3 = profilePic3;
    }
}

