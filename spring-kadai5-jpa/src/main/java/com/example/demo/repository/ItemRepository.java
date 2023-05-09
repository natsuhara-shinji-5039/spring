package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findAllByOrderByPrice();
	List<Item> findByPriceLessThanEqual(Integer price);
	List<Item> findByNameLike(String keyword);
	List<Item> findByPriceLessThanEqualAndNameLike(Integer price, String keyword);
	List<Item> findByPriceLessThanEqualOrderByPrice(Integer price);
	List<Item> findByNameLikeOrderByPrice(String keyword);
	List<Item> findByPriceLessThanEqualAndNameLikeOrderByPrice(Integer price, String keyword);
}
