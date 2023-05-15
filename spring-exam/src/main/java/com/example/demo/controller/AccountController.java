package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {
	@Autowired
	HttpSession session;
	
	@Autowired
	Account account;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping({"/login", "/logout"})
	public String index() {
		session.invalidate();
		return "login";
	}
	
	@PostMapping("/login")
	public String login(
			@RequestParam(name="email", defaultValue="") String email,
			@RequestParam(name="password", defaultValue="") String password,
			Model model) {
		List<User> users = userRepository.findByEmailAndPassword(email, password);
		if(users.size() == 0) {
			model.addAttribute("err", "メールアドレスとパスワードが一致しませんでした");
			return "login";
		} else {
			account.setName(users.get(0).getName());
			return "redirect:/users";
		}
	}
}
