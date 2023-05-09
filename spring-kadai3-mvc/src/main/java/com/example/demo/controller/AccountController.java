package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;

@Controller
public class AccountController {
	@GetMapping("/account")
	public String index(Model model) {
		Account account = new Account();
		model.addAttribute("name", account.getName());
		model.addAttribute("email", account.getEmail());
		return "accountForm";
	}
	
	@PostMapping("/account/confirm")
	public String confirm(
			@RequestParam(name="name", defaultValue="") String name,
			@RequestParam(name="email", defaultValue="") String email,
			@RequestParam(name="password", defaultValue="") String password,
			Model model) {
		Account account = new Account(name, email, password);
		// データ保持
		model.addAttribute("name", account.getName());
		model.addAttribute("email", account.getEmail());
		
		List<String> errorLists = new ArrayList<>();
		// エラーチェック名前
		if(name == null || name.isEmpty()) {
			errorLists.add("名前は必須です");
		} else if(name.length() > 20) {
			errorLists.add("名前は20文字以内で登録してください");
		}
		// エラーチェックメールアドレス
		if(email == null || email.isEmpty()) {
			errorLists.add("メールアドレスは必須です");
		}
		// エラーチェックパスワード
		if(password == null || password.isEmpty()) {
			errorLists.add("パスワードは必須です");
		}
		
		// 
		if(errorLists.size() == 0 ) {
			// エラーなし
			model.addAttribute("account", account);
		} else {
			// エラーあり
			model.addAttribute("errorLists", errorLists);
			return "accountForm";
		}
		return "accountConfirm";
	}
	
	@PostMapping("/account")
	public String store(
			@RequestParam(name="name", defaultValue="") String name,
			@RequestParam(name="email", defaultValue="") String email,
			@RequestParam(name="password", defaultValue="") String password,
			Model model) {
		Account account = new Account(name, email);
		model.addAttribute("account", account);
		return "accountFinish";
	}
}
