package com.linkable.linkable;

public class User {
    private String userid;
    private String email;
    private String name;
    private String password;
    private String token;

    public User(String userid,String email,String name,String password){
<<<<<<< HEAD
        this.userid = userid;
        this.email = email;
        this.name = name;
        this.password = password;
=======
        this.userid=userid;
        this.email=email;
        this.name=name;
        this.password=password;
>>>>>>> 710840a97b6eb56276bca52b4efa4fc1cfb4ccf7
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