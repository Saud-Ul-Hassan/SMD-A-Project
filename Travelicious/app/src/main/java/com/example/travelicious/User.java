package com.example.travelicious;

public class User {

    String name;
    String email;
    String password;
    public User(String n,String e,String p)
    {
        this.name=n;
        this.email=e;
        this.password=p;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public User() {

    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
