package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {
	// 登録画面
	@GetMapping("/item")
	public String index() {
		return "/item/itemForm";
	}
	
	@GetMapping("/item/hidden")
	public String itemFromHidden() {
		return "item/itemFormHidden";
	}
	
	// 登録情報確認画面の表示
	@PostMapping("/item")
	public String confirm(
			@RequestParam(name = "name", defaultValue = "未設定") String name,
			@RequestParam(name ="price", defaultValue = "100") int price,
			Model model) {
		model.addAttribute("name", name);
		model.addAttribute("price", price);
		return "item/itemConfirm";
	}
	
	@GetMapping("/item/{id}")
	public String show(
			@PathVariable("id") int id,
			Model model) {
		switch (id) {
		case 101:
			model.addAttribute("name", "ボールペン");
			model.addAttribute("price", 100);
			break;
		case 102:
			model.addAttribute("name", "消しゴム");
			model.addAttribute("price", 50);
			break;
		default:
			model.addAttribute("name", "未設定");
			model.addAttribute("price", 0);
			break;
		}
		
		return "item/itemConfirm";
	}
	
	// 商品詳細登録画面の表示
	@GetMapping("/item/detail")
	public String detail() {
		return "item/itemDetailForm";
	}
	
	// 商品詳細確認画面
	@PostMapping("/item/detail")
	public String confirmDetail(
			@RequestParam(name = "name", defaultValue = "未設定") String name,
			@RequestParam(name = "price", defaultValue = "") Integer price,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "genre", defaultValue = "") String[] genreList,
			@RequestParam(name = "releaseDate", defaultValue = "")
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate releaseDate,
			Model model) {
		
		model.addAttribute("name", name);
		model.addAttribute("price", price);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("genreList", genreList);
		model.addAttribute("releaseDate", releaseDate);
		
		return "item/itemDetailConfirm";
	}
}









