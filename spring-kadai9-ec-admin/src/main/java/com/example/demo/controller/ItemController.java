package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.model.Account;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	@Autowired
	Account account;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ItemRepository itemRepository;

	// 商品一覧表示
	@GetMapping("/items")
	public String index(
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			@RequestParam(value = "maxPrice", defaultValue = "") Integer maxPrice,
			Model model) {

		// 全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		// 商品一覧情報の取得
		List<Item> itemList = null;
		if (categoryId == null && keyword.equals("") && maxPrice == null) {
			itemList = itemRepository.findAll();
		} else if(!keyword.equals("") && maxPrice != null) {
			itemList = itemRepository.findByNameLikeAndPriceLessThanEqualOrderByPrice("%" + keyword + "%", maxPrice);
		} else if(!keyword.equals("")) {
			itemList = itemRepository.findByNameLike("%" + keyword + "%");
		} else if(maxPrice != null) {
			itemList = itemRepository.findByPriceLessThanEqual(maxPrice);
		} else {
			// itemsテーブルをカテゴリーIDを指定して一覧を取得
			itemList = itemRepository.findByCategoryId(categoryId);
		}
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("keyword", keyword);
		model.addAttribute("items", itemList);

		return "items";
	}
	
	// 商品詳細ページ
	@GetMapping("/items/{id}")
	public String show(
			@PathVariable("id") Integer id,
			Model model) {
		if(id != null) {
			Item item = itemRepository.findById(id).get();
			model.addAttribute(item);
			return "itemDetail";
		} else {
			return "redirect:/items";
		}
	}
}
