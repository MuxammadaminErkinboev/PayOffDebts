package com.B31.Muxammadamin.controller;

import com.B31.Muxammadamin.entity.Market;
import com.B31.Muxammadamin.service.DebtService;
import com.B31.Muxammadamin.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/superadmin")
public class SuperAdminController {
    private final MarketService marketService;
    private final DebtService debtService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("markets", marketService.findAll());
        model.addAttribute("totalDebt", debtService.calculateTotalDebt());
        return "superadmin/dashboard";
    }

    @PostMapping("/addMarket")
    public String addMarket(@RequestParam String name, @RequestParam String password) {
        Market market = new Market();
        market.setName(name);
        market.setPassword(password);
        marketService.save(market);
        return "redirect:/superadmin/dashboard";
    }
}
