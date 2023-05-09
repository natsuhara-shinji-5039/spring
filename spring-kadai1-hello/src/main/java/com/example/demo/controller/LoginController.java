package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String index() {
		return "login/index";
	}
	
	@PostMapping("/login")
	public String login(
			@RequestParam(name="userId") String id,
			@RequestParam(name="password") String pass,
			Model model) {
		int status;
		if(pass.equals("himitu") || id.equals("student")) {
			status = 1;
			model.addAttribute("status", status);
		} else {
			model.addAttribute("comment", "パスワードが一致しませんでした");
			status = 0;
			model.addAttribute("status", status);
		}
		return "login/login";
	}
}
