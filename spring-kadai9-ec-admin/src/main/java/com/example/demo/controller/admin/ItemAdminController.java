package com.example.demo.controller.admin;

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
public class ItemAdminController {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/admin/items")
	public String index(
			@RequestParam(name="categoryId", defaultValue="") Integer categoryId,
			Model model) {
		List<Item> items = null;
		if(categoryId != null) {
			items = itemRepository.findByCategoryId(categoryId);
		} else {
			items = itemRepository.findAll();
		}
		// 全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);
		model.addAttribute("items", items);
		return "admin/items";
	}
	
	@GetMapping("/admin/items/add")
	public String create(Model model) {
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);
		return "admin/addItem";
	}
	
	@PostMapping("/admin/items/add")
	public String store(
			@RequestParam(name="categoryId", defaultValue="") Integer categoryId,
			@RequestParam(name="name", defaultValue="") String name,
			@RequestParam(name="price", defaultValue="") Integer price
			) {
		Item item = new Item(categoryId, name, price);
		itemRepository.save(item);
		return "redirect:/admin/items";
	}
	
	@GetMapping("/admin/items/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			Model model) {
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);
		Item item = itemRepository.findById(id).get();
		model.addAttribute("item", item);
		return "/admin/editItem";
	}
	
	@PostMapping("/admin/items/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name="categoryId", defaultValue="") Integer categoryId,
			@RequestParam(name="name", defaultValue="") String name,
			@RequestParam(name="price", defaultValue="") Integer price
			) {
		Item item = new Item(id, categoryId, name, price);
		itemRepository.save(item);
		return "redirect:/admin/items";
	}
	
	@PostMapping("/admin/items/{id}/delete")
	public String delete(
			@PathVariable("id") Integer id
			) {
		itemRepository.deleteById(id);
		System.out.println("test");
		return "redirect:/admin/items";
	}
	
}
