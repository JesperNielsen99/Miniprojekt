package com.example.miniproject_wishlist.services;

import com.example.miniproject_wishlist.repositories.IWishlistRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    private final IWishlistRepository repository;

    public WishlistService(ApplicationContext context, @Value("${superhelt.repository.impl}") String impl) {
        repository = (IWishlistRepository) context.getBean(impl);
    }


}
