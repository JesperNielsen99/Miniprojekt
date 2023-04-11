package com.example.miniproject_wishlist.services;

import com.example.miniproject_wishlist.dto.LoginDTO;
import com.example.miniproject_wishlist.models.User;
import com.example.miniproject_wishlist.repositories.IWishlistRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final IWishlistRepository repository;

    public LoginService(ApplicationContext context, @Value("${wishlist.repository.impl}") String impl) {
        repository = (IWishlistRepository) context.getBean(impl);
    }

    public User getUser(String email, String password){
        return repository.getUser(email, password);
    }
}
