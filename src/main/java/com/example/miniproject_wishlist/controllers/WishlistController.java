package com.example.miniproject_wishlist.controllers;

import com.example.miniproject_wishlist.models.Wish;
import com.example.miniproject_wishlist.services.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path="/")
public class WishlistController {
    private WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping(path="wishes")
    public String getWishes(Model model) {
        List<Wish> wishes = wishlistService.getWishes();
        model.addAttribute("wishlist", wishes);
        return "index";
    }

}
