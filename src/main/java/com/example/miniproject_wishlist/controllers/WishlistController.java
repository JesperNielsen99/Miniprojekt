package com.example.miniproject_wishlist.controllers;

import com.example.miniproject_wishlist.dto.*;
import com.example.miniproject_wishlist.models.*;
import com.example.miniproject_wishlist.models.Wishlist;
import com.example.miniproject_wishlist.repositories.WishlistRepository_DB;
import com.example.miniproject_wishlist.services.WishlistService;
import jakarta.servlet.http.HttpSession;
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


    @GetMapping(path="wishlists")
    public String getWishlists(HttpSession session, @PathVariable String email, Model model) {
        if (isLoogedIn(session)) {
            List<Wishlist> wishlists = wishlistService.getWishlists(new EmailDTO(email));
            model.addAttribute("email", email);
            model.addAttribute("wishlists", wishlists);
            return "wishlists";
        }
        return "login";
    }

    @GetMapping(path = "addwishlist")
    public String addWishlistForm(HttpSession session, @PathVariable String  email, Model model){
        if (isLoogedIn(session)) {
            Wishlist wishlist = new Wishlist();
            wishlist.setEmail(new EmailDTO(email));
            model.addAttribute("newWishlist", wishlist);
            return "addWishlistForm";
        }
        return "login";
    }

    @PostMapping(path = "addwishlist/{email}")
    public String addWishlistSubmit(HttpSession session, @PathVariable String  email, @ModelAttribute("newWishlist") Wishlist wishlist){
        if (isLoogedIn(session)) {
            wishlistService.addWishlist(wishlist);
            return "redirect:/wishlists/" + email;
        }
        return "login";
    }

    @GetMapping(path = "addwish/{email}")
    public String addWishForm(HttpSession session, @PathVariable String email, Model model) {
        if (isLoogedIn(session)) {
            WishDTO wish = new WishDTO();
            List<Wishlist> wishlists = wishlistService.getWishlists(new EmailDTO(email));
            wish.setWishlists(wishlists);
            model.addAttribute("newWish", wish);
            return "addWishForm";
        }
        return "login";
    }

    @PostMapping(path = "addwish/{email}")
    public String addWishSubmit(HttpSession session, @PathVariable String email, @ModelAttribute("newWish") WishDTO wish) {
        if (isLoogedIn(session)) {
            wishlistService.addWishToWishlist(wish);
            return "redirect:wishes/" + email;
        }
        return "login";
    }

    @GetMapping(path = "/wishlists/{wishlistName}/{email}")
    public String getWishesFromWishlist( HttpSession session,@PathVariable String wishlistName, @PathVariable String email, Model model) {
        if (isLoogedIn(session)) {
            List<Wish> wishes = wishlistService.getWishes(new EmailDTO(email));
            model.addAttribute("email", email);
            model.addAttribute("wishes", wishes);
            model.addAttribute("wishlistName", wishlistName);
            return "wishes";
        }
        return "login";
    }


    private boolean isLoogedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    public String showAdm1(HttpSession session) {
        return isLoogedIn(session) ? "admin1" : "login";
    }
}
