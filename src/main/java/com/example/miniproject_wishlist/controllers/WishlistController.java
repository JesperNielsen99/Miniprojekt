package com.example.miniproject_wishlist.controllers;

import com.example.miniproject_wishlist.dto.*;
import com.example.miniproject_wishlist.models.*;
import com.example.miniproject_wishlist.models.Wishlist;
import com.example.miniproject_wishlist.repositories.WishlistRepository_DB;
import com.example.miniproject_wishlist.services.WishlistService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/")
public class WishlistController {
    private WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping(path="wishlists/{email}")
    public String getWishlists(@PathVariable String email, Model model) {
        List<Wishlist> wishlists = wishlistService.getWishlists(new EmailDTO(email));
        model.addAttribute("email", email);
        model.addAttribute("wishlists", wishlists);
        return "wishlists";
    }

    @GetMapping(path = "addwishlist/{email}")
    public String addWishlistForm(@PathVariable String  email, Model model){
        Wishlist wishlist = new Wishlist();
        wishlist.setEmail(new EmailDTO(email));
        model.addAttribute("newWishlist", wishlist);
        return "addWishlistForm";
    }

    @PostMapping(path = "addwishlist/{email}")
    public String addWishlistSubmit(@PathVariable String  email, @ModelAttribute("newWishlist") Wishlist wishlist){
        wishlistService.addWishlist(wishlist);
        return "redirect:/wishlists/" + email;
    }

    @GetMapping(path = "addwish/{email}")
    public String addWishForm(@PathVariable String email, Model model) {
        WishDTO wish = new WishDTO();
        List<Wishlist> wishlists = wishlistService.getWishlists(new EmailDTO(email));
        wish.setWishlists(wishlists);
        model.addAttribute("newWish", wish);
        return "addWishForm";
    }

    @PostMapping(path = "addwish/{email}")
    public String addWishSubmit(@PathVariable String email, @ModelAttribute("newWish") WishDTO wish) {
        wishlistService.addWishToWishlist(wish);
        return "redirect:wishes/" + email;
    }

    @GetMapping(path = "/wishlists/{wishlistName}/{email}")
    public String getWishesFromWishlist(@PathVariable String wishlistName, @PathVariable String email, Model model) {
        List<Wish> wishes = wishlistService.getWishes(new EmailDTO(email));
        model.addAttribute("email", email);
        model.addAttribute("wishes", wishes);
        model.addAttribute("wishlistName", wishlistName);
        return "wishes";
    }




}
