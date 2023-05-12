package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {
	List<Stock> findByProductIdEquals(Integer productId);
}
