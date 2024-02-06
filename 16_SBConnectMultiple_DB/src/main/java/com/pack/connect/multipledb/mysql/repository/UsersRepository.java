package com.pack.connect.multipledb.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.connect.multipledb.mysql.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	Users findByFirstName(String firstName);
}
