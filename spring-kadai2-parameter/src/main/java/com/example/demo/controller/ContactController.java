package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {
	
	@GetMapping("/contact")
	public String index(Model model) {
		
		
		return "/contactForm";
	}
	
	@PostMapping("/contact")
	public String contact(
			@RequestParam(name="gener", defaultValue="") Integer genre,
			@RequestParam(name="lang", defaultValue="") String[] langList,
			@RequestParam(name="detail", defaultValue="") String detail,
			@RequestParam(name="dueDate", defaultValue="")
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dueDate,
			@RequestParam(name="name", defaultValue="") String name,
			@RequestParam(name="email", defaultValue="") String email,
			Model model) {
		List<String> list = new ArrayList<String>();
		LocalDate date = LocalDate.now();
		boolean flag = true;
		if(langList == null || langList.length == 0) {
			list.add("言語は必須です");
			flag = false;
		}
		if(dueDate == null) {
			list.add("実施予定日を選択して下さい");
			flag = false;
		} else if(dueDate.isBefore(date)) {
			list.add("実施予定日は翌日以降を選択してください");
			flag = false;
		}
		if(name == null || name.isEmpty()) {
			list.add("名前は20文字で以内で入力してください");
			flag = false;
		}
		if(email == null || email.isEmpty()) {
			list.add("メールアドレスは必須です");
			flag = false;
		}
		
		
		if(flag) {
			model.addAttribute("langList", langList);
			model.addAttribute("detail", detail);
			model.addAttribute("dueDate", dueDate);
			model.addAttribute("name", name);
			model.addAttribute("email", email);
			return "/contactResult";
		} else {
			model.addAttribute("detail", detail);
			model.addAttribute("name", name);
			model.addAttribute("email", email);
			model.addAttribute("lists", list);
			return "/contactForm";
		}
	}
	
	@GetMapping("/traning/{lang}")
	public String traning(
			@PathVariable("lang") String lang,
			Model model) {
		model.addAttribute("lang", lang);
		if(lang.equals("Java")) {
			model.addAttribute("body", "大規模開発でオールラウンドに活躍できるエンジニアを育成します");
		} else if(lang.equals("PHP")) {
			model.addAttribute("body", "Webアプリケーションに特化して活躍できるエンジニアを育成します");
		} else if(lang.equals("Python")) {
			model.addAttribute("body", "AI開発やデータ分析の領域で活躍できるエンジニアを育成します");
		}
		return "traning";
	}
}
