package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalcController {
	@GetMapping("calc")
	public String calc() {
		return "calc";
	}
	
	@GetMapping("/add")
	public String index(
			@RequestParam("num1") Integer num1,
			@RequestParam("num2") Integer num2,
			Model model) {
		Integer result = 0;
		result = num1 + num2;
		model.addAttribute("result", result);
		return "calc";
	}
}
