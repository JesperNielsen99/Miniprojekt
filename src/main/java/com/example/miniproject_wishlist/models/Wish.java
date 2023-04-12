package com.example.miniproject_wishlist.models;

import java.util.ArrayList;
import java.util.List;

public class Wish {
    private String wishName;
    private String wishLink;
    private List<Wishlist> wishlists = new ArrayList<>();

    public Wish() {}

    public Wish(String wishName, String wishLink, List<Wishlist> wishlists) {
        this.wishName = wishName;
        this.wishLink = wishLink;
        this.wishlists = wishlists;
    }

    public String getWishName() { return wishName; }
    public String getWishLink() { return wishLink; }
    public List<Wishlist> getWishlists() { return wishlists; }

    public void addWishlist(Wishlist wishlist){ wishlists.add(wishlist); }

    public void setWishName(String wishName) { this.wishName = wishName; }
    public void setWishLink(String wishLink) { this.wishLink = wishLink; }
    public void setWishlists(List<Wishlist> wishlists) { this.wishlists = wishlists; }
}
