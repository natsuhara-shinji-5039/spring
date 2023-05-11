package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	// SELECT * FROM items WHERE category_id = ?
	List<Item> findByCategoryId(Integer categoryId);
	List<Item> findByNameLikeAndPriceLessThanEqualOrderByPrice(String keyword, Integer maxPrice);
//	List<Item> findByPriceLessThanEqualAndNameLikeOrderByPrice(Integer price, String keyword);
	List<Item> findByNameLike(String keyword);
	List<Item> findByPriceLessThanEqual(Integer maxPrice);
}