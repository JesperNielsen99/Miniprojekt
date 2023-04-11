package com.example.miniproject_wishlist.models;

public class User {
    private int userID;
    private String userName;
    private String email;
    private String password;


    public User() {}

    public User(int userID, String userName, String email, String password) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getUserName() { return userName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public int getUserID() { return userID; }

    public void setUserName(String userName) { this.userName = userName; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setUserID(int userID) { this.userID = userID; }
}
