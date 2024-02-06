package com.pack.connect.multipledb.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.connect.multipledb.h2.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByName(String name);
}
