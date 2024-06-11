package com.example.stock_monitor.controller;

import com.example.stock_monitor.model.Stock;
import com.example.stock_monitor.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("stock", new Stock());
        return "index";
    }

    @PostMapping("/addStock")
    public String addStock(@ModelAttribute Stock stock) {
        stockService.saveStock(stock);
        return "redirect:/";
    }

    @GetMapping("/stocks")
    public String viewStocks(Model model) {
        List<Stock> stocks = stockService.getAllStocks();
        model.addAttribute("stocks", stocks);
        return "stocks";
    }

    @GetMapping("/history")
    public String viewHistory(Model model) {
        // Implement history view logic
        return "history";
    }
}
