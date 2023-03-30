package com.example.miniproject_wishlist.services;

import com.example.miniproject_wishlist.dto.EmailDTO;
import com.example.miniproject_wishlist.models.Wish;
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

    public List<Wish> getWishes(EmailDTO email) { return repository.getWishes(email); }

    public List<String> getEmails() { return repository.getEmails(); }

    public void addWishlist(wishlistDTO){
        repository.addWishlist(wishlistDTO);
    }
}
