package com.B31.Muxammadamin.controller;

import com.B31.Muxammadamin.entity.User;
import com.B31.Muxammadamin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String registrationPage() {
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        try {
            userService.register(user);
            return "redirect:/user/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "user/register";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            User user = userService.authenticate(username, password);
            model.addAttribute("user", user);
            return "redirect:/user/dashboard";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "user/login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(@RequestParam int userId, Model model) {
        model.addAttribute("debts", userService.findUserDebts(userId));
        return "user/dashboard";
    }
}

