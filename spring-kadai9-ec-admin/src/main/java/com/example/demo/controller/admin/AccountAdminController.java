package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;

@Controller
public class AccountAdminController {
	
	@Autowired
	Account account;
	
	@GetMapping("/admin/login")
	public String index() {
		return "admin/login";
	}
	
	@PostMapping("/admin/login")
	public String login(
			@RequestParam(name="name", defaultValue="") String name,
			@RequestParam(name="password", defaultValue="") String password,
			Model model) {
		if(!name.equals("admin") || !password.equals("himitu")) {
			System.out.println("test");
			model.addAttribute("err", "ユーザー名とパスワードが一致しませんでした");
			return "admin/login";
		}
		System.out.println("test2");
		// セッション管理されたアカウント情報に名前をセット
		account.setName(name);
		return "redirect:/admin/items";
	}
}
