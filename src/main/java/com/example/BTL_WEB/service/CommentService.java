package com.example.BTL_WEB.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BTL_WEB.book.Comment;
import com.example.BTL_WEB.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository repo;
	
	public List<Comment> getCommentByBook_id(Integer book_id) {
		return repo.getCommentByBook_id(book_id);
	}
	
	@Transactional
	public void deleteCommentByUserID(Integer user_id) {
		repo.deleteCommentByUserID(user_id);
	}
	
	@Transactional
	public void deleteCommentByBookID(Integer book_id) {
		repo.deleteCommentByBookID(book_id);
	}
	
	public void save(Comment comment) {
		repo.save(comment);
	}
}
