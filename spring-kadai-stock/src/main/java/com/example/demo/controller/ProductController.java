package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Product;
import com.example.demo.entity.Stock;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StockRepository;

@Controller
public class ProductController {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	StockRepository stockRepository;
	
	@GetMapping("/products")
	public String index(Model model) {
		List<Product> products = productRepository.findAll();
		model.addAttribute("products", products);
		for(Product product: products) {
			List<Stock> stocks = stockRepository.findByProductIdEquals(product.getId());
			for(Stock stock: stocks) {
				Integer total = stock.getStoring() - stock.getIssue();
				System.out.println(stock.getStoring());
				product.setStock(total);
			}
		}
		return "products";
	}
	
	@GetMapping("/products/{id}")
	public String show(
			@PathVariable("id") Integer id,
			Model model) {
		Product product = productRepository.findById(id).get();
		List<Stock> stocks = stockRepository.findByProductIdEquals(id);
		
		for(Stock stock: stocks) {
			Integer total = stock.getStoring() - stock.getIssue();
			System.out.println(stock.getStoring());
			product.setStock(total);
		}
		
		model.addAttribute("product", product);
		model.addAttribute("stocks", stocks);
		return "productsDetail";
	}
	
	
	
	
}
