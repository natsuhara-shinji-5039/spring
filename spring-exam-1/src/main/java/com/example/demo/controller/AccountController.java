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

	//ログイン画面表示・ログアウト処理
	@GetMapping({ "/login", "/logout" })
	public String index() {
		session.invalidate();

		return "login";
	}

	//ログイン処理
	@PostMapping("/login")
	public String login(
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {

		if (email.equals("") || password.equals("")) {
			model.addAttribute("errorMsg", "メールアドレスとパスワードを入力してください");
			return "/login";
		}

		List<User> userList = userRepository.findByEmailAndPassword(email, password);
		if (userList == null || userList.size() == 0) {
			model.addAttribute("errorMsg", "メールアドレスとパスワードが一致しませんでした");
			return "/login";
		}

		User user = userList.get(0);
		account.setId(user.getId());
		account.setName(user.getName());

		return "redirect:/users";
	}

}
