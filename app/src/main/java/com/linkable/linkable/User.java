package com.linkable.linkable;

public class User {
    private String userid;
    private String email;
    private String name;
    private String password;
    private String token;

    public User(String userid,String email,String name,String password){
        this.userid=userid;
        this.email=email;
        this.name=name;
        this.password=password;
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

    public String getUserid() {
        return userid;
    }

    public String getToken() {
        return token;
    }
}