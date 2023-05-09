package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自動採番
	private Integer id; // 商品ID
	@Column(name = "category_id") // テーブルのカラム名が異なる場合
	private Integer categoryId; // カテゴリーID
	private String name; // 商品名
	private Integer price; // 価格
	
	// ゲッター
	public Integer getId() {
		return id;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getPrice() {
		return price;
	}
	
}
