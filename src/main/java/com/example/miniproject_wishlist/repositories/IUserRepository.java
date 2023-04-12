package com.example.miniproject_wishlist.repositories;

import com.example.miniproject_wishlist.models.User;

public interface IUserRepository {
    User createUser(User user);
    User updateUser(User user);
    User DeleteUser(User user);
    User getUser(String email, String password);
}
