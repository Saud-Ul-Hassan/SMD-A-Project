package com.example.travelicious;


import android.os.Parcel;
import android.os.Parcelable;

public class Profile implements Parcelable {
    private String email;
    private String name;
    private String profilePic;
    private boolean permission;

    public Profile() {
    }

    public Profile(String email, String name, String profilePic) {
        this.email = email;
        this.name = name;
        this.profilePic = profilePic;
    }

    public Profile(String email, String name, String profilePic, boolean permission) {
        this.email = email;
        this.name = name;
        this.profilePic = profilePic;
        this.permission = permission;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public boolean getPermission() {
        return permission;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(profilePic);
        //dest.writeB(permission);

    }
}
