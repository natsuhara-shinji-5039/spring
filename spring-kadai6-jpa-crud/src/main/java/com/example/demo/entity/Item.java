package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "category_id")
	private Integer categoryId;
	private String name;
	private Integer price;
	
	@OneToOne
    @JoinColumn(name="category_id", referencedColumnName = "id", nullable=false, insertable = false, updatable = false)
    private Category category;
//	@OneToOne
//	@JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
//	@NotFound(action = NotFoundAction.IGNORE)
//	private Category category;
	
	
	
	public Category getCategory() {
		return category;
	}

	// コンストラクタ
	public Item() {
		
	}
	
	// 新規登録
	public Item(Integer categoryId, String name, Integer price) {
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
	}
	
	// 更新
	public Item(Integer id, Integer categoryId, String name, Integer price) {
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
	}
	
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
