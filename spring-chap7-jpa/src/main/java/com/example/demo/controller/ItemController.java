package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	CategoryRepository categoryRepository;

	// 商品一覧表示
	@GetMapping("/")
	public String index(Model model) {
		// itemsテーブルから全商品の一覧を取得
		List<Item> itemList = itemRepository.findAll();
		// Thymeleafにデータを渡す
		model.addAttribute("items", itemList);
		// item.htmlを出力
		return "items";
	}

	// 商品一覧表示（カテゴリーによる絞り込み）
	@GetMapping("/items")
	public String items(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {

		// categoryテーブルから全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		// 商品一覧情報の取得
		List<Item> itemList = null;
		if (categoryId == null) {
			itemList = itemRepository.findAll();
		} else {
			// itemsテーブルをカテゴリーIDを指定して一覧を取得
			itemList = itemRepository.findByCategoryId(categoryId);
		}
		model.addAttribute("items", itemList);

		return "items";
	}
}
