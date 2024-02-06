package com.pack.connect.multipledb.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.connect.multipledb.h2.entities.Product;
import com.pack.connect.multipledb.h2.repository.ProductRepository;
import com.pack.connect.multipledb.mysql.entities.Users;
import com.pack.connect.multipledb.mysql.repository.UsersRepository;

@RestController
public class ApplicationController {

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@PostMapping("/save")
	public String saveData() {
		userRepository.saveAll(
				Stream.of(new Users("Honnur", "Ali", "honnurali68@gmail.com"), new Users("ABC", "Xyz", "abc@gmail.com"))
						.collect(Collectors.toList()));
		productRepository
				.saveAll(Stream
						.of(new Product("Realme", "Smart Phone", true, 10000.00),
								new Product("Realme Narzo", "Smart Phone", false, 12000.00))
						.collect(Collectors.toList()));

		return "Data Saved";
	}

	@GetMapping("/getUsers")
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/getProducts")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
}
