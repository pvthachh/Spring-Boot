package com.example.BTL_WEB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BTL_WEB.book.User;
import com.example.BTL_WEB.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	
	public List<User> getAllsExceptAdmin() {
		return repo.getAllsExceptAdmin();
	}
	
	public void delete(Integer user_id) {
		repo.delete(repo.findById(user_id).get());
	}
	
	public User getAccount(String username, String password) {
		return repo.getAccount(username, password);
	}
	
	public boolean isExisted(User user) {
		User user2 = repo.getAccountsByUsername(user.getUsername());
		if(user2 != null) return true;
		return false;
	}
	
	public void save(User user) {
		repo.save(user);
	}

}
