package com.example.miniproject_wishlist.dto;

public class WishlistDTO {
    private String wishlistName;
    private EmailDTO email;

    public WishlistDTO() {}

    public WishlistDTO(String wishlistName, EmailDTO email) {
        this.wishlistName = wishlistName;
        this.email = email;
    }

    public String getWishlistName() { return wishlistName; }
    public EmailDTO getEmail() { return email; }

    public void setWishlistName(String wishlistName) { this.wishlistName = wishlistName; }
    public void setEmail(EmailDTO email) { this.email = email; }
}
