package com.example.stock_monitor.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.util.concurrent.*;

public class WebScrapeStock {
    public static double getStockPrice(String stockSymbol){
        try {
            String stockPrice = sgetStockPrice(stockSymbol);
            if (stockPrice != null) {
                System.out.println("The current price of " + stockSymbol + " is: " + stockPrice);
                return Double.parseDouble(stockPrice);
            } else {
                System.out.println("Could not retrieve the stock price for " + stockSymbol);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while fetching the stock price: " + e.getMessage());
        }
        return 0;
    }
    public static void task() {
        getStockPrice("TSLA");
    }
    public static void task2() {
        System.out.println("Task 2 has completed");
    }
    public static void main(String[] args) {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(WebScrapeStock::task, 0, 15, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(WebScrapeStock::task2, 0, 5, TimeUnit.SECONDS);

    }

    public static String sgetStockPrice(String stockSymbol) throws IOException {
        String url = "https://finance.yahoo.com/quote/" + stockSymbol;
        Document doc = Jsoup.connect(url).get();

        // The CSS selector below might change, so inspect the page if it stops working
        Element priceElement = doc.selectFirst("fin-streamer[data-field='regularMarketPrice']");

        if (priceElement != null) {
            return priceElement.text();
        } else {
            return null;
        }
    }
}