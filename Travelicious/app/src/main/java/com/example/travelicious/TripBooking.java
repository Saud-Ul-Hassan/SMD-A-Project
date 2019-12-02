package com.example.travelicious;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class TripBooking {
    String UserName;
    String HotelName;
    String CountryName;
    String image;
    String rooms;
    String members;
    String checkIn;
    String checkOut;

    public TripBooking(String userName, String hotelName, String countryName, String image, String rooms, String members, String checkIn, String checkOut) {
        UserName = userName;
        HotelName = hotelName;
        CountryName = countryName;
        this.image = image;
        this.rooms = rooms;
        this.members = members;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public TripBooking(String userName, String hotelName, String countryName, String img) {
        UserName = userName;
        HotelName = hotelName;
        CountryName = countryName;
        image=img;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public TripBooking() {
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("UserName", UserName);
        result.put("HotelName", HotelName);
        result.put("CountryName", CountryName);
        result.put("members", members);
        result.put("rooms", rooms);
        result.put("checkIn", checkIn);
        result.put("checkOut", checkOut);

        return result;
    }
}
