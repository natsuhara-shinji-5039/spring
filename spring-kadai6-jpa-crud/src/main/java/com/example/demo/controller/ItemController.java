package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	// 一覧ページ
	@GetMapping("/items")
	public String index(Model model) {
		List<Item> itemList = itemRepository.findAll();
		model.addAttribute("items", itemList);
		return "/items";
	}
	
	// 新規登録ページ
	@GetMapping("/items/add")
	public String create(Model model) {
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);
		return "addItems";
	}
	
	// 新規登録処理
	@PostMapping("/items/add")
	public String store(
			@RequestParam(name="categoryId", defaultValue="") Integer categoryId,
			@RequestParam(name="name", defaultValue="") String name,
			@RequestParam(name="price", defaultValue="") Integer price
			) {
		Item item = new Item(categoryId, name, price);
		itemRepository.save(item);
		return "redirect:/items";
	}
	
	// 編集ページ
	@GetMapping("/items/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			Model model) {
		Item item = itemRepository.findById(id).get();
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("item", item);
		model.addAttribute("categories", categories);
		return "editItems";
	}
	
	// 編集処理
	@PostMapping("/items/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name="categoryId", defaultValue="") Integer categoryId,
			@RequestParam(name="name", defaultValue="") String name,
			@RequestParam(name="price", defaultValue="") Integer price
			) {
		Item item = new Item(id, categoryId, name, price);
		itemRepository.save(item);
		return "redirect:/items";
	}
	
	// 削除
	@PostMapping("/items/{id}/delete")
	public String delete(@PathVariable("id") Integer id) {
		itemRepository.deleteById(id);
		return "redirect:/items";
	}
	
	
	
	
}
