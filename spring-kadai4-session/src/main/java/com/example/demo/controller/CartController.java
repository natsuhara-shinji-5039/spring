package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.model.Cart;
import com.example.demo.model.Item;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	@Autowired
	HttpSession session;
	
	@Autowired
	Account account;
	
	@Autowired
	Cart cart;
	
	@GetMapping("/cart")
	public String showCart(Model model) {
		model.addAttribute("default", "カートに追加");
		return "cart";
	}
	
	@PostMapping("/cart/add")
	public String addCart(
			@RequestParam(name="name", defaultValue="") String name,
			@RequestParam(name="price", defaultValue="") Integer price,
			Model model) {
		
		List<Item> allCart = cart.getItems();
		allCart.add(new Item(name, price));
		
		model.addAttribute("displayFlag", true);
		model.addAttribute("cart", allCart);
		return "cart";
	}
	
	@GetMapping("/cart/clear")
	public String clearCart() {
		List<Item> allCart = cart.getItems();
		allCart.clear();
//		session.invalidate();
		return "/cart";
	}
	
	@GetMapping({"/cart/login", "/cart/logout"})
	public String index() {
		session.invalidate();
		return "/cartLogin";
	}
	
	@PostMapping("/cart/login")
	public String login(
			@RequestParam(name="name", defaultValue="") String name,
			Model model) {
		account.setName(name);
		model.addAttribute("default", "カートに追加");
		return "/cart";
	}
	
	
}
