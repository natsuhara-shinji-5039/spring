package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.RandomStringService;

@Controller
public class RandomStringController {
	
	@Autowired // Springによるオブジェクトの管理(注入)
	RandomStringService service;
	
	@GetMapping("/random")
	public String index() {
		return "random/random";
	}
	
	@PostMapping("/randoms")
	public String generate(
			@RequestParam("charLength") int charLength,
			@RequestParam(name = "withNumber", defaultValue = "false") boolean withNumber,
			Model model) {
		// ランダム文字列のlistを生成
		List<String> list = service.generate(charLength, withNumber);
		model.addAttribute("randList", list);
		return "random/random";
	}
}




