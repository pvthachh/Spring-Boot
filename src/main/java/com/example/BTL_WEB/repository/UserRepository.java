package com.example.BTL_WEB.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BTL_WEB.book.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("SELECT p FROM User p WHERE p.role <> 'ADMIN'")
	List<User> getAllsExceptAdmin();
	
	@Query("select p from User p where p.username = ?1 and p.password = ?2")
	User getAccount(String username, String password);
	
	@Query("select p from User p where p.username = ?1")
	User getAccountsByUsername(String username);
}
