package com.example.BTL_WEB.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BTL_WEB.book.Order_detail;
import com.example.BTL_WEB.repository.Order_detailRepository;

@Service
public class Order_detailService {
	@Autowired
	private Order_detailRepository repo;
	
	public List<Order_detail> getOrderByUserID(Integer user_id) {
		return repo.getOrderByUserID(user_id);
	}
	
	@Transactional
	public void deleteByUser_id(Integer user_id) {
		repo.deleteByUser_id(user_id);
	}
	
	@Transactional
	public void deleteByBook_id(Integer book_id) {
		repo.deleteByBook_id(book_id);
	}
	
	public Order_detail getOrderByUserIDAndBookID(Integer user_id, Integer book_id) {
		return repo.getOrderByUserIDAndBookID(user_id, book_id);
	}
	
	@Transactional
	public void deleteByUserIdAndBookId(Integer user_id, Integer book_id) {
		repo.deleteByUserIdAndBookId(user_id, book_id);
	}
	
	public void save(Order_detail order_detail) {
		repo.save(order_detail);
	}
}
