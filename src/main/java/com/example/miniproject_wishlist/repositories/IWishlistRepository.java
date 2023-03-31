package com.example.miniproject_wishlist.repositories;

import com.example.miniproject_wishlist.dto.*;
import com.example.miniproject_wishlist.dto.WishDTO;
import com.example.miniproject_wishlist.models.Wishlist;
import com.example.miniproject_wishlist.models.*;

import java.util.List;

public interface IWishlistRepository {

    List<Wishlist> getAllWishlists(EmailDTO email);

    void addWishlist(Wishlist wishlist);

    List<Wish> getWishes(EmailDTO email);

    List<String> getEmails();

    void addWish(WishDTO wish);


}
