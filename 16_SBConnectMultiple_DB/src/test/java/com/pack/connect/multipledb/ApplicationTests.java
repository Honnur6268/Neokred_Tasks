package com.pack.connect.multipledb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pack.connect.multipledb.h2.entities.Product;
import com.pack.connect.multipledb.h2.repository.ProductRepository;
import com.pack.connect.multipledb.mysql.entities.Users;
import com.pack.connect.multipledb.mysql.repository.UsersRepository;

@SpringBootTest
class ApplicationTests {

	@Autowired
	UsersRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void saveData() {
		Users user = new Users( "Honnur", "Ali","honnurali68@gmail.com");
		Product product = new Product("Realme", "Realme Smart phone", true, 25000.00);
		
//		userRepository.save(user);
//		productRepository.save(product);
		
		if(userRepository.save(user)!=null && productRepository.save(product)!=null) {
			System.out.println("saved.....");
		}
		else {
			System.out.println("Some error...");
		}	
	}
	
	@Test
	public void getData() {
		Users  user = userRepository.findByFirstName("Honnur");
		Product product = productRepository.findByName("Realme");
		
		if(user!=null && product!=null) {
			System.out.println("Users --> "+user);
			System.out.println("prodcut --> "+product);
		}
		else {
			System.out.println("Error during fetching!!!");
		}
	}
}
