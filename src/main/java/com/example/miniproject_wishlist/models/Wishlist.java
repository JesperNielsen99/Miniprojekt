package com.example.miniproject_wishlist.models;

public class Wishlist {
    private String wishlistName;
    private int userID;

    public Wishlist() {}

    public Wishlist(String wishlistName, int UserID) {
        this.wishlistName = wishlistName;
        this.userID = userID;
    }

    public String getWishlistName() { return wishlistName; }
    public int getUserID() { return userID; }

    public void setWishlistName(String wishlistName) { this.wishlistName = wishlistName; }
    public void setUserID(int userID) { this.userID = userID; }
}
