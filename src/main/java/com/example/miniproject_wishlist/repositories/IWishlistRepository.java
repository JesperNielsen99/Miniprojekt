package com.example.miniproject_wishlist.repositories;

import com.example.miniproject_wishlist.dto.EmailDTO;
import com.example.miniproject_wishlist.dto.WishDTO;
import com.example.miniproject_wishlist.models.Wishlist;
import com.example.miniproject_wishlist.models.Wish;

import java.util.List;

public interface IWishlistRepository {

    List<Wishlist> getAllWishlists(EmailDTO email);

    void addWishlist(Wishlist wishlist);

    List<Wish> getWishes(EmailDTO email);

    List<String> getEmails();

    void addWish(WishDTO wish);
}
