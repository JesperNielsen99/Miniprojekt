package com.example.miniproject_wishlist.models;

public class Wishlist {
    private String wishlistName;
    private int wishlistID;
    private int userID;

    public Wishlist() {}

    public Wishlist(String wishlistName, int wishlistID, int UserID) {
        this.wishlistName = wishlistName;
        this.wishlistID = wishlistID;
        this.userID = userID;
    }

    public String getWishlistName() { return wishlistName; }
    public int getUserID() { return userID; }
    public int getWishlistID() { return wishlistID; }

    public void setWishlistName(String wishlistName) { this.wishlistName = wishlistName; }
    public void setUserID(int userID) { this.userID = userID; }
    public void setWishlistID(int wishlistID) { this.wishlistID = wishlistID; }
}
