package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Cart {
	// 商品一覧
	private List<Item> items = new ArrayList<>();
	
	// ゲッター
	public List<Item> getItems() {
		return items;
	}
}
