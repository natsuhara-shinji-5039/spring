package com.example.demo.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stocks")
public class Stock {
	private final static DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "create_time")
	private LocalDateTime createTime;
	
	private Integer issue = 0;
	private Integer storing = 0;;
	
	public Stock() {
		createTime = LocalDateTime.now();
	}
	
	public void setIssue(Integer productId, Integer issue) {
		this.productId = productId;
		this.issue = issue;
	}
	
	public void setStoring(Integer productId, Integer storing) {
		this.productId = productId;
		this.storing = storing;
	}
	
	public Integer getIssue() {
		return issue;
	}
	
	public Integer getStoring() {
		return storing;
	}
	
	public String getCreateTime() {
		return createTime.format(FMT);
	}
}
