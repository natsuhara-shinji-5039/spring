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

	// 商品一覧表示（追加課題）
	@GetMapping("/items")
	public String index(
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "maxPrice", defaultValue = "") Integer maxPrice,
			@RequestParam(name = "sort", defaultValue = "") String sort,
			Model model) {

		// 商品一覧情報の取得
		List<Item> itemList = null;
		if (keyword.length() > 0) {
			// キーワードあり
			if (maxPrice != null) {
				// 最大価格あり
				if (sort.equals("priceAsc")) {
					itemList = itemRepository.findByNameContainingAndPriceLessThanEqualOrderByPriceAsc(keyword,
							maxPrice);
				} else {
					itemList = itemRepository.findByNameContainingAndPriceLessThanEqual(keyword, maxPrice);
				}
			} else {
				// 最大価格なし
				if (sort.equals("priceAsc")) {
					itemList = itemRepository.findByNameContainingOrderByPriceAsc(keyword);
				} else {
					itemList = itemRepository.findByNameContaining(keyword);
				}
			}

		} else {
			// キーワードなし
			if (maxPrice != null) {
				// 最大価格あり
				if (sort.equals("priceAsc")) {
					itemList = itemRepository.findByPriceLessThanEqualOrderByPriceAsc(maxPrice);
				} else {
					itemList = itemRepository.findByPriceLessThanEqual(maxPrice);
				}
			} else {
				// 最大価格なし
				if (sort.equals("priceAsc")) {
					itemList = itemRepository.findAllByOrderByPriceAsc();
				} else {
					itemList = itemRepository.findAll();
				}
			}
		}

		if (maxPrice == null) {
			// ※安い順リンクを「th:href="@{/items(...」で作成した場合、このif文の考慮不要。
			model.addAttribute("maxPrice", "");
		} else {
			model.addAttribute("maxPrice", maxPrice);
		}

		model.addAttribute("keyword", keyword);

		model.addAttribute("items", itemList);
		return "items";
	}
}
