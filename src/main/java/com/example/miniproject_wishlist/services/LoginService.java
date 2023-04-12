package com.example.miniproject_wishlist.services;

import com.example.miniproject_wishlist.models.User;
import com.example.miniproject_wishlist.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final IUserRepository userRepository;

    public LoginService(ApplicationContext context, @Value("User_DB") String impl) {
        userRepository = (IUserRepository) context.getBean(impl);
    }

    public User getUser(String email, String password){
        return userRepository.getUser(email, password);
    }
}