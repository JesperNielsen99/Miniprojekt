package com.example.miniproject_wishlist.dto;

import com.example.miniproject_wishlist.models.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class WishDTO {
    private String wishName;
    private String wishLink;
    private List<Wishlist> wishlists = new ArrayList<>();

    public WishDTO() {
    }

    public String getWishName() {
        return wishName;
    }


    public String getWishLink() {
        return wishLink;
    }

    public List<Wishlist> getWishlists() {
        return wishlists;
    }

    public void addWishlist(Wishlist wishlist){
        wishlists.add(wishlist);
    }


    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public void setWishLink(String wishLink) {
        this.wishLink = wishLink;
    }

    public void setWishlists(List<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }
}

