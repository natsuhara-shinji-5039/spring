package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/hello")
	public String input() {
		return "input";
	}
	
	@PostMapping("/hello")
	public String show(
			@RequestParam(name = "name", defaultValue = "未設定") String name,
			@RequestParam(name = "age", defaultValue = "100") Integer age,
			@RequestParam(name = "hobby", defaultValue="無趣味") String hobby,
			Model model) {
		String memo;
		if(age > 18) {
			memo = "成人してから" + (age - 18) + "年たちました";
		} else if(age == 18) {
			memo = "成人しました";
		} else {
			memo = "未成年です";
		}
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		model.addAttribute("memo", memo);
		model.addAttribute("hobby", hobby);
		
		return "hello";
	}
}
