package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
	
	@Autowired
	ItemRepository itemRepository;
	
	// 商品一覧ページ
	@GetMapping("/items")
	public String index(
			@RequestParam(name="sort", defaultValue = "") String sort,
			@RequestParam(name="maxPrice", defaultValue="") Integer maxPrice,
			@RequestParam(name="keyword", defaultValue="") String keyword,
			Model model) {
		String url = "/items?sort=priceAsc";
		List<Item> itemList = null;
		if(maxPrice != null && !keyword.equals("") && sort.equals("priceAsc")) {
			// 金額以下、キーワード取得、安い順
			itemList = itemRepository.findByPriceLessThanEqualAndNameLikeOrderByPrice(maxPrice, "%" + keyword + "%");
		} else if(maxPrice != null &&  sort.equals("priceAsc")) {
			// 金額以下、安い順
			itemList = itemRepository.findByPriceLessThanEqualOrderByPrice(maxPrice);
		} else if(!keyword.equals("") && sort.equals("priceAsc")) {
			// キーワード取得、安い順
			itemList = itemRepository.findByNameLikeOrderByPrice("%" + keyword + "%");
		} else if(maxPrice != null && !keyword.equals("")) {
			// 金額以下、キーワード取得
			itemList = itemRepository.findByPriceLessThanEqualAndNameLike(maxPrice, "%" + keyword + "%");
			url = "/items?maxPrice=" + maxPrice + "&keyword=" + keyword + "&sort=priceAsc";
		}else if(sort.equals("priceAsc")) {
			// 安い順
			itemList = itemRepository.findAllByOrderByPrice();
		} else if(maxPrice != null) {
			// 金額値以下取得
			itemList = itemRepository.findByPriceLessThanEqual(maxPrice);
			url = "/items?maxPrice=" + maxPrice + "&sort=priceAsc";
		} else if(!keyword.equals("")) {
			// キーワード取得
			itemList = itemRepository.findByNameLike("%" + keyword + "%");
			url = "/items?keyword=" + keyword + "&sort=priceAsc";
		} else {
			// すべてのデータ取得
			itemList = itemRepository.findAll();
		}
		model.addAttribute("url", url);
		model.addAttribute("price", maxPrice);
		model.addAttribute("keyword", keyword);
		model.addAttribute("items", itemList);
		return "items";
	}
	
}
