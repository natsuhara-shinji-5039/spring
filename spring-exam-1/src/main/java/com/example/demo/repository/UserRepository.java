package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByNameLikeOrderById(String name);

	List<User> findAllByOrderById();

	List<User> findByEmailAndPassword(String email, String password);
}
