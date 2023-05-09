package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
//	@GetMapping("/hello") // GETリクエストの
//	public String index() {
//		// hello.htmlを出力する
//		return "hello/hello";
//	}
	
	@GetMapping("/hello")
	public String hello(@RequestParam("msg") String message, Model model) {
		// 画面出力
		model.addAttribute("memo", message);
		// hello.htmlの出力
		return "hello/hello";
	}
	
	// POST リクエスト
	@PostMapping("/hello")
	public String helloByPost(@RequestParam("msg") String message, Model model) {
		// 画面に情報を渡す
		model.addAttribute("memo", message);
		// HelloPost.htmlの出力
		return "hello/helloPost";
	}
}
