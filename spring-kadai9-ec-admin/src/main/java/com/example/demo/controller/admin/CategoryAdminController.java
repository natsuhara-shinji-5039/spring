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
import com.example.demo.repository.CategoryRepository;

@Controller
public class CategoryAdminController {
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/admin/categories")
	public String index(Model model) {
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);
		return "admin/categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String create() {
		return "admin/addCategory";
	}
	
	@PostMapping("/admin/categories/add")
	public String store(
			@RequestParam(name="name") String name,
			Model model) {
		Category category = new Category(name);
		categoryRepository.save(category);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			Model model) {
		Category category = categoryRepository.findById(id).get();
		model.addAttribute("category", category);
		return "admin/editCategory";
	}
	
	@PostMapping("/admin/categories/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name="name", defaultValue="") String name,
			Model model) {
		Category category = new Category(id, name);
		categoryRepository.save(category);
		return "redirect:/admin/categories";
	}
	
	@PostMapping("/admin/categories/{id}/delete")
	public String delete(
			@PathVariable("id") Integer id,
			Model model) {
		categoryRepository.deleteById(id);
		return "redirect:/admin/categories";
	}
}
