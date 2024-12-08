package com.B31.Muxammadamin.controller;

import com.B31.Muxammadamin.entity.Debt;
import com.B31.Muxammadamin.entity.Market;
import com.B31.Muxammadamin.service.DebtService;
import com.B31.Muxammadamin.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketController {

    private final MarketService marketService;
    private final DebtService debtService;

    @GetMapping("/login")
    public String loginPage() {
        return "market/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password, Model model) {
        try {
            Market market = marketService.authenticate(name, password);
            model.addAttribute("market", market);
            return "redirect:/market/dashboard";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "market/login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(@RequestParam int marketId, Model model) {
        model.addAttribute("debts", debtService.findByMarketId(marketId));
        return "market/dashboard";
    }
}

