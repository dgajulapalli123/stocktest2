package com.example.stock_monitor.service;

import com.example.stock_monitor.model.Stock;
import com.example.stock_monitor.repository.StockRepository;
import com.example.stock_monitor.scraper.WebScrapeStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;


    private WebScrapeStock webScrapeStock; // Move @Autowired here

    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Scheduled(fixedRate = 60000)
    public void updateStockPrices() {
        List<Stock> stocks = stockRepository.findAll();
        for (Stock stock : stocks) {
            double currentPrice = webScrapeStock.getStockPrice(stock.getSymbol()); // Use injected instance
            stock.setPrice(currentPrice);
            if (currentPrice >= stock.getThreshold()) {
                // Add logic for real-time notification
                System.out.println("Threshold reached for " + stock.getSymbol());
            }
            stockRepository.save(stock);
        }
    }
}
