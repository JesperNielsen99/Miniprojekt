package com.example.miniproject_wishlist.repositories;

import com.example.miniproject_wishlist.models.Wishlist;
import com.example.miniproject_wishlist.models.*;

import java.util.List;

public interface IWishlistRepository {

    List<Wishlist> getAllWishlists(User user);
    Wishlist getWishlist(int wishlistID, User user);

    void addWishlist(Wishlist wishlist);

    List<Wish> getWishes(int wishlistID);

    void addWish(Wish wish);
}
