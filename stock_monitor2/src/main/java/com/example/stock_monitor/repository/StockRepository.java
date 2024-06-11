package com.example.stock_monitor.repository;

import com.example.stock_monitor.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
