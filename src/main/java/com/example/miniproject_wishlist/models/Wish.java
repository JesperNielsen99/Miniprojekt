package com.example.miniproject_wishlist.models;

import java.util.ArrayList;
import java.util.List;

public class Wish {
    private int wishID;
    private String wishName;
    private String wishLink;
    private int wishlistID;

    public Wish() {}

    public Wish(int wishID, String wishName, String wishLink, int wishlistID) {
        this.wishID = wishID;
        this.wishName = wishName;
        this.wishLink = wishLink;
        this.wishlistID = wishlistID;
    }

    public int getWishlistID() { return wishlistID; }
    public String getWishName() { return wishName; }
    public String getWishLink() { return wishLink; }
    public int getWishID() { return wishID; }
    public void setWishName(String wishName) { this.wishName = wishName; }
    public void setWishLink(String wishLink) { this.wishLink = wishLink; }
    public void setWishID(int wishID) { this.wishID = wishID; }
    public void setWishlistID(int wishlistID) { this.wishlistID = wishlistID; }
}
