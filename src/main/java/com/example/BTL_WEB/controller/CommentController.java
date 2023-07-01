package com.example.BTL_WEB.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BTL_WEB.book.Comment;
import com.example.BTL_WEB.book.User;
import com.example.BTL_WEB.service.BookService;
import com.example.BTL_WEB.service.CommentService;


@Controller
public class CommentController {
	@Autowired
	private BookService bookService;
	@Autowired
	private CommentService commentService;
	
	
	@GetMapping("/save/comment")
	public String addComment(@RequestParam("rating") Integer star, 
			@RequestParam("content") String content
			, @RequestParam("book_id") Integer book_id, HttpSession session) {
		Comment comment = new Comment();
		User user = (User) session.getAttribute("userLogin");
		comment.setUser(user);
		comment.setBook(bookService.getByID(book_id));
		comment.setContent(content);
		comment.setStar(star);
		commentService.save(comment);
		return "redirect:/detail/" + String.valueOf(book_id);
	}
}
