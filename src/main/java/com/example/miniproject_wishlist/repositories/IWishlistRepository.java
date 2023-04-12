package com.example.miniproject_wishlist.repositories;

import com.example.miniproject_wishlist.models.Wishlist;
import com.example.miniproject_wishlist.models.*;

import java.util.List;

public interface IWishlistRepository {

    List<Wishlist> getAllWishlists(User user);

    void addWishlist(Wishlist wishlist);

    List<Wish> getWishes(User user);

    void addWish(Wish wish);


}
