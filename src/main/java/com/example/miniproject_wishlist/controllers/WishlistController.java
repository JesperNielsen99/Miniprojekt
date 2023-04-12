package com.example.miniproject_wishlist.controllers;

import com.example.miniproject_wishlist.models.*;
import com.example.miniproject_wishlist.models.Wishlist;
import com.example.miniproject_wishlist.services.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/")
public class WishlistController {
    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }


    @GetMapping(path="wishlists")
    public String getWishlists(HttpSession session, Model model) {
        if (isLoggedIn(session)) {
            List<Wishlist> wishlists = wishlistService.getWishlists((User) session.getAttribute("user"));
            model.addAttribute("wishlists", wishlists);
            return "wishlists";
        }
        return "login";
    }

    @GetMapping(path = "addwishlist")
    public String addWishlistForm(HttpSession session, Model model){
        if (isLoggedIn(session)) {
            Wishlist wishlist = new Wishlist();
            User user = (User) session.getAttribute("user");
            wishlist.setUserID(user.getUserID());
            model.addAttribute("newWishlist", wishlist);
            return "addWishlistForm";
        }
        return "login";
    }

    @PostMapping(path = "addwishlist")
    public String addWishlistSubmit(HttpSession session, @ModelAttribute("newWishlist") Wishlist wishlist){
        if (isLoggedIn(session)) {
            wishlistService.addWishlist(wishlist);
            return "redirect:/wishlists/";
        }
        return "login";
    }

    @GetMapping(path = "addwish")
    public String addWishForm(HttpSession session, Model model) {
        if (isLoggedIn(session)) {
            Wish wish = new Wish();
            List<Wishlist> wishlists = wishlistService.getWishlists((User) session.getAttribute("user"));
            wish.setWishlists(wishlists);
            model.addAttribute("newWish", wish);
            return "addWish";
        }
        return "login";
    }

    @PostMapping(path = "addwish")
    public String addWishSubmit(HttpSession session, @ModelAttribute("newWish") Wish wish) {
        if (isLoggedIn(session)) {
            wishlistService.addWishToWishlist(wish);
            return "redirect:wishes/";
        }
        return "login";
    }

    @GetMapping(path = "/wishlists/{wishlistName}")
    public String getWishesFromWishlist(HttpSession session,@PathVariable String wishlistName, Model model) {
        if (isLoggedIn(session)) {
            List<Wish> wishes = wishlistService.getWishes((User) session.getAttribute("user"));
            model.addAttribute("wishes", wishes);
            model.addAttribute("wishlistName", wishlistName);
            return "wishes";
        }
        return "login";
    }

}
