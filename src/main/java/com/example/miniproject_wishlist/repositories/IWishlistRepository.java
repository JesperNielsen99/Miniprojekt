package com.example.miniproject_wishlist.repositories;

import com.example.miniproject_wishlist.dto.EmailDTO;
import com.example.miniproject_wishlist.models.Wish;

import java.util.List;

public interface IWishlistRepository {
    //int getUserID(EmailDTO email);

    List<Wish> getWishes(EmailDTO email);

    List<String> getEmails();
}
