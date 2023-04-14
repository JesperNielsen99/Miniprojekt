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

    @GetMapping(path = "wishes/{wishlistID}")
    public String getWishesFromWishlist(HttpSession session, Model model, @PathVariable int wishlistID ) {
        if (isLoggedIn(session)) {
            Wishlist wishlist = wishlistService.getWishlist(wishlistID);
            List<Wish> wishes = wishlistService.getWishes(wishlistID);
            model.addAttribute("wishes", wishes);
            model.addAttribute("wishlistName", wishlist.getWishlistName());
            model.addAttribute("wishlistID", wishlistID);
            return "wishes";
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

    @GetMapping(path = "addwish/{wishlistID}")
    public String addWishForm(HttpSession session, Model model, @PathVariable int wishlistID) {
        if (isLoggedIn(session)) {
            Wish wish = new Wish();
            wish.setWishlistID(wishlistID);
            model.addAttribute("newWish", wish);
            return "addWish";
        }
        return "redirect:/login";
    }

    @PostMapping(path = "addwish/{wishlistID}")
    public String addWishSubmit(HttpSession session, @ModelAttribute("newWish") Wish wish, @PathVariable int wishlistID) {
        if (isLoggedIn(session)) {
            wishlistService.addWishToWishlist(wish, wishlistID);
            return "redirect:/wishes/" + wishlistID;
        }
        return "redirect:/login";
    }

    @PostMapping(path = "/delete/wish/{wishID}/{wishlistID}")
    public String deleteWish(HttpSession session, @PathVariable int wishID, @PathVariable int wishlistID){
        if (isLoggedIn(session)){
            wishlistService.deleteWish(wishID);
            return "redirect:/wishes/" + wishlistID;
        }
        return "redirect:/login";
    }
}
