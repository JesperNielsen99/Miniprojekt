package com.example.miniproject_wishlist.services;

import com.example.miniproject_wishlist.models.*;
import com.example.miniproject_wishlist.models.Wishlist;
import com.example.miniproject_wishlist.repositories.IWishlistRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    private final IWishlistRepository repository;

    public WishlistService(ApplicationContext context, @Value("${wishlist.repository.impl}") String impl) {
        repository = (IWishlistRepository) context.getBean(impl);
    }

    public List<Wishlist> getWishlists(User user) { return repository.getAllWishlists(user); }

    public List<Wish> getWishes(User user) { return repository.getWishes(user); }


    public void addWishToWishlist(Wish wish) { repository.addWish(wish); }

    public void addWishlist(Wishlist wishlist) { repository.addWishlist(wishlist); }
}
