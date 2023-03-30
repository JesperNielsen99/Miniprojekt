package com.example.miniproject_wishlist.dto;

import java.util.ArrayList;
import java.util.List;

public class WishDTO {
    private String wishName;
    private String wishLink;
    private List<WishlistDTO> wishlists = new ArrayList<>();

    public WishDTO() {
    }

    public String getWishName() {
        return wishName;
    }

    public String getWishLink() {
        return wishLink;
    }

    public List<WishlistDTO> getWishlists() {
        return wishlists;
    }

    public void addWishlist(WishlistDTO wishlist){
        wishlists.add(wishlist);
    }


    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public void setWishLink(String wishLink) {
        this.wishLink = wishLink;
    }

    public void setWishlists(List<WishlistDTO> wishlists) {
        this.wishlists = wishlists;
    }
}

