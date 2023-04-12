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
        return "redirect:/login";
    }

    @GetMapping(path = "addwishlist")
    public String addWishlistForm(HttpSession session, Model model){
        if (isLoggedIn(session)) {
            Wishlist wishlist = new Wishlist();
            model.addAttribute("wishlist", wishlist);
            return "addWishlistForm";
        }
        return "redirect:/login";
    }

    @PostMapping(path = "addwishlist")
    public String addWishlistSubmit(HttpSession session, @ModelAttribute("wishlist") Wishlist wishlist){
        if (isLoggedIn(session)) {
            User user = (User) session.getAttribute("user");
            wishlist.setUserID(user.getUserID());
            wishlistService.addWishlist(wishlist);
            return "redirect:/wishlists";
        }
        return "redirect:/login";
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
        return "redirect:/login";
    }

    @PostMapping(path = "addwish")
    public String addWishSubmit(HttpSession session, @ModelAttribute("newWish") Wish wish) {
        if (isLoggedIn(session)) {
            wishlistService.addWishToWishlist(wish);
            return "redirect:/wishes";
        }
        return "redirect:/login";
    }

    @GetMapping(path = "wishes")
    public String getWishesFromWishlist(HttpSession session,@PathVariable Wishlist wishlist, Model model) {
        if (isLoggedIn(session)) {
            List<Wish> wishes = wishlistService.getWishes((User) session.getAttribute("user"));
            model.addAttribute("wishes", wishes);
            model.addAttribute("wishlist", wishlist);
            return "wishes";
        }
        return "redirect:/login";
    }

}
