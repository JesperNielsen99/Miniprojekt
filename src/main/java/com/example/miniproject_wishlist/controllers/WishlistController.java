package com.example.miniproject_wishlist.controllers;

import com.example.miniproject_wishlist.dto.EmailDTO;
import com.example.miniproject_wishlist.models.Wish;
import com.example.miniproject_wishlist.repositories.WishlistRepository_DB;
import com.example.miniproject_wishlist.services.WishlistService;
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

    @GetMapping(path="wishes/{email}")
    public String getWishes(@PathVariable String  email, Model model) {
        List<Wish> wishes = wishlistService.getWishes(new EmailDTO(email));
        model.addAttribute("wishlist", wishes);
        return "wishlists";
    }

    @GetMapping(path="login")
    public String logIn(Model model) {
        List<String> emails = wishlistService.getEmails();
        EmailDTO emailDTO = new EmailDTO();
        model.addAttribute("newEmail", emailDTO);
        model.addAttribute("emails", emails);
        return "login";
    }

    @PostMapping(path="login")
    public String logInSubmit(EmailDTO emailDTO) {
        return "redirect:/wishes/" + emailDTO.getEmail();
    }

    @GetMapping(path = "addwishlist")
    public String addWishlistForm(Model model){
        model.addAttribute("newWishlist",new WishlistDTO());
        return "addWishlistForm";
    }

    @PostMapping(path = "addwishlist")
    public String addWishlistSubmit(@ModelAttribute("WishlistDTO") WishlistDTO wishlistDTO){
        WishlistService.addWishlistSubmit();
        return "redirect:wishes/{email}";
    }



}
