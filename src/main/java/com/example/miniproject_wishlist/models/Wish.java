package com.example.miniproject_wishlist.models;

public class Wish {
    private String wishName;
    private String wishLink;

    public Wish() {}

    public Wish(String wishName, String wishLink) {
        this.wishName = wishName;
        this.wishLink = wishLink;
    }

    public String getWishName() { return wishName; }
    public String getWishLink() { return wishLink; }

    public void setWishName(String wishName) { this.wishName = wishName; }
    public void setWishLink(String wishLink) { this.wishLink = wishLink; }
}
