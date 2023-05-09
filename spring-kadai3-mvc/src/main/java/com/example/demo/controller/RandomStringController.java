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
		return "random";
	}
	
	@PostMapping("/random")
	public String generate(
			@RequestParam("charLength") int charLength,
			@RequestParam(name = "withNumber", defaultValue = "false") boolean withNumber,
			@RequestParam(name = "withEnglish", defaultValue = "false") boolean withEnglish,
			@RequestParam(name = "createNum", defaultValue = "0") int createNum,
			Model model) {
		model.addAttribute("charLength", charLength);
		model.addAttribute("withNumber", withNumber);
		model.addAttribute("withEnglish", withEnglish);
		model.addAttribute("createNum", createNum);
		
		// ランダム文字列のlistを生成
		List<String> list = service.generate(charLength, withNumber, withEnglish, createNum);
		model.addAttribute("randList", list);
		return "random";
	}
}




