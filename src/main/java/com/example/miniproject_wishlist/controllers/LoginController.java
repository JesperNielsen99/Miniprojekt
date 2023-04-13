package com.example.miniproject_wishlist.controllers;

import com.example.miniproject_wishlist.models.User;
import com.example.miniproject_wishlist.services.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping("/login")
    public String showLogin() {
        // return login form
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password,
                        HttpSession session, Model model)
    {
        // find user in repo - return wishlists if success
        User user = loginService.getUser(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(30);
            return "wishlists";
        }
        // wrong credentials
        model.addAttribute("wrongCredentials", true);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session and return landing page
        session.invalidate();
        return "login";
    }

    @GetMapping("/profile")
    public String profile (Model model, HttpSession session){
        User user = (User) session.getAttribute("user");

        model.addAttribute("user",user);

        return "profile";
    }


}
