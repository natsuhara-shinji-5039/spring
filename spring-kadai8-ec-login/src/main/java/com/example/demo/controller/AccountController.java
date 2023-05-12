package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;
import com.example.demo.model.Account;
import com.example.demo.repository.CustomerRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;
	
	@Autowired
	CustomerRepository customerRepository;

	// ログイン画面を表示
	@GetMapping({ "/", "/login", "/logout" })
	public String index(
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		// セッション情報を全てクリアする
		session.invalidate();
		// エラーパラメータのチェック
		if (error.equals("notLoggedIn")) {
			model.addAttribute("message", "ログインしてください");
		}

		return "login";
	}

	// ログインを実行
	@PostMapping("/login")
	public String login(
			@RequestParam(name="email", defaultValue="") String email,
			@RequestParam(name="password", defaultValue="") String password,
			Model model) {
		List<String> errors = new ArrayList<String>();
		List<Customer> customer = customerRepository.findByEmail(email);
		// 名前が空の場合にエラーとする
		if (email.equals("")) {
			errors.add("メールアドレスを入力してください");
//			model.addAttribute("message", "名前を入力してください");
//			return "login";
		} else if(customer.size() == 0) {
			errors.add("アカウントが存在しません");
		} else if(customer.size() != 0 && !customer.get(0).getPassword().equals(password)) {
			errors.add("パスワードが一致しません");
		}
		
		if(password.equals("") && customer != null) {
			errors.add("パスワードを入力してください");
		}
		
		if(errors.size() == 0) {
			// セッション管理されたアカウント情報に名前をセット
			account.setCustomer(customer.get(0));

			// 「/items」へのリダイレクト
			return "redirect:/items";
		} else {
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			model.addAttribute("errors", errors);
			return "login";
		}
	}
	
	// 新規登録
	@GetMapping("/account")
	public String create() {
		return "accountForm";
	}
	
	@PostMapping("/account")
	public String store(
			@RequestParam(name="name", defaultValue="") String name,
			@RequestParam(name="address", defaultValue="") String address,
			@RequestParam(name="tel", defaultValue="") String tel,
			@RequestParam(name="email", defaultValue="") String email,
			@RequestParam(name="password", defaultValue="") String password,
			Model model) {
		List<String> err = new ArrayList<String>();
		if(name.equals("")) {
			err.add("名前は必須です");
		}
		if(address.equals("")) {
			err.add("住所は必須です");
		}
		if(tel.equals("")) {
			err.add("電話番号は必須です");
		}
		if(email.equals("")) {
			err.add("メールアドレスは必須です");
		}
		if(password.equals("")) {
			err.add("パスワードは必須です");
		}
		
		List<Customer> checkCustomer = customerRepository.findByEmail(email);
		if(checkCustomer.size() > 0) {
			err.add("登録済みのメールアドレスです");
		}
		
		if(err.size() == 0) {
			System.out.println("test1");
			Customer customer = new Customer(name, address, tel, email, password);
			customerRepository.save(customer);
			return "redirect:/login";
		} else {
			model.addAttribute("errors", err);
			return "accountForm";
		}
		
	}
}
