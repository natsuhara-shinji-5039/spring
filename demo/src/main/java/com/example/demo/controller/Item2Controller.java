package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Item;

@Controller
public class Item2Controller {
	// 初期画面表示
	@GetMapping("/item2")
	public String index() {
		return "item2/item";
	}
	
	// 登録ボタンクリック時
	@PostMapping("/item2")
	public String add(
			@RequestParam(name = "name", defaultValue="") String name,
			@RequestParam(name = "price", defaultValue="") Integer price,
			Model model) {
		
		// Itemクラスのオブジェクト (モデル) を生成
		Item item = new Item(name, price);
		model.addAttribute("item", item);
		return "item2/item";
	}
}
