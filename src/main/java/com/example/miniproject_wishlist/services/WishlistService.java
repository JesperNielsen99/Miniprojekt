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

    public WishlistService(ApplicationContext context, @Value("Wishlist_DB") String impl) {
        repository = (IWishlistRepository) context.getBean(impl);
    }

    public List<Wishlist> getWishlists(User user) { return repository.getAllWishlists(user); }

    public Wishlist getWishlist(int wishlistID){ return repository.getWishlist(wishlistID);}

    public List<Wish> getWishes(int wishlistID) { return repository.getWishes(wishlistID); }

    public void addWishToWishlist(Wish wish, int WishlistID) { repository.addWish(wish, WishlistID); }

    public void addWishlist(Wishlist wishlist) { repository.addWishlist(wishlist); }

    public void deleteWish(int wishID) { repository.deleteWish(wishID); }

    public void deleteWishlist(int wishlistID) { repository.deleteWishlist(wishlistID); }
}
