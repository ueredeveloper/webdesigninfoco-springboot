package com.ueredeveloper.webdesigninfoco.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ueredeveloper.webdesigninfoco.com.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByNameAndPassword(String name, String password);
}
