package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	HttpSession session;

	@Autowired
	Account account;
	@Autowired
	UserRepository userRepository;

	//顧客一覧画面表示
	@GetMapping("/users")
	public String index(
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			Model model) {
		List<User> userList = null;

		if (keyword.equals("")) {
			userList = userRepository.findAllByOrderById();
		} else {
			userList = userRepository.findByNameLikeOrderById("%" + keyword + "%");
		}
		model.addAttribute("keyword", keyword);
		model.addAttribute("users", userList);

		return "users";
	}

	//新規登録画面表示
	@GetMapping("/users/add")
	public String create() {
		return "addUser";
	}

	//新規登録処理
	@PostMapping("/users/add")
	public String store(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {

		if (name.equals("") || email.equals("") || password.equals("")) {
			model.addAttribute("errorMsg", "名前とメールアドレスとパスワードを入力してください");
			model.addAttribute("name", name);
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			return "/addUser";
		}

		User user = new User(name, email, password);
		userRepository.save(user);

		return "redirect:/users";
	}

	//更新画面表示
	@GetMapping("/users/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model) {
		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);

		return "editUser";
	}

	//更新処理
	@PostMapping("/users/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password) {

		User user = new User(id, name, email, password);
		userRepository.save(user);

		return "redirect:/users";
	}

	//削除処理
	@PostMapping("/users/{id}/delete")
	public String delete(@PathVariable("id") Integer id) {
		userRepository.deleteById(id);
		return "redirect:/users";
	}
}
