package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Product;
import com.example.demo.entity.Stock;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StockRepository;

@Controller
public class StockController {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	StockRepository stockRepository;
	
	@PostMapping("/storing/{productId}")
	public String storing(
			@PathVariable("productId") Integer productId,
			@RequestParam(name="storing", defaultValue="") Integer storing,
			Model model) {
		if(storing == null) {
			Product product = productRepository.findById(productId).get();
			List<Stock> stocks = stockRepository.findByProductIdEquals(productId);
			
			for(Stock stock: stocks) {
				Integer total = stock.getStoring() - stock.getIssue();
				System.out.println(stock.getStoring());
				product.setStock(total);
			}
			
			model.addAttribute("product", product);
			model.addAttribute("stocks", stocks);
			model.addAttribute("err", "入庫数を入力してください");
			return "productsDetail";
		} else {
			Stock stock = new Stock();
			stock.setStoring(productId, storing);
			stockRepository.save(stock);
			return "redirect:/products/" + productId;
		}
	}
	
	@PostMapping("/issue/{productId}")
	public String issue(
			@PathVariable("productId") Integer productId,
			@RequestParam(name="issue", defaultValue="") Integer issue,
			Model model) {
		if(issue == null) {
			Product product = productRepository.findById(productId).get();
			List<Stock> stocks = stockRepository.findByProductIdEquals(productId);
			
			for(Stock stock: stocks) {
				Integer total = stock.getStoring() - stock.getIssue();
				System.out.println(stock.getStoring());
				product.setStock(total);
			}
			
			model.addAttribute("product", product);
			model.addAttribute("stocks", stocks);
			model.addAttribute("err", "出庫数を入力してください");
			return "productsDetail";
		} else {
			Stock stock = new Stock();
			stock.setIssue(productId, issue);
			stockRepository.save(stock);
			return "redirect:/products/" + productId;
		}
	}
	
	
	
	
	
}
