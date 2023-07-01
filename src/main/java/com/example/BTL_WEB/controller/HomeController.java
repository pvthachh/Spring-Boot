package com.example.BTL_WEB.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.BTL_WEB.book.Book;
import com.example.BTL_WEB.book.Comment;
import com.example.BTL_WEB.book.User;
import com.example.BTL_WEB.service.BookService;
import com.example.BTL_WEB.service.CommentService;
import com.example.BTL_WEB.service.Order_detailService;

@Controller
public class HomeController {
	@Autowired
	private BookService bookService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private Order_detailService order_detailService;
	
	@GetMapping("/home")
	public String getBooks(Model model) throws IOException{
		List<BookExpand> listBookExpands0 = new ArrayList<>();
		List<Book> list1 = bookService.getAll();
		for (Book b : list1) {
			listBookExpands0.add(new BookExpand(b, getAverageStar(b)));
		}
		model.addAttribute("listB", listBookExpands0);
		return "Home";
	}
	
	@GetMapping("/detail/{id}")
	public String getDetailProduct(@PathVariable Integer id, Model model) {
		Book book = bookService.getByID(id);
		model.addAttribute("detailBook", book);
		setAverageStar(model, id);
		return "Detail";
	}
	
	@GetMapping("/manage")
	public String managePage(HttpSession session) {
		User user = (User) session.getAttribute("userLogin");
		if(user == null) return "ERROR";
		if(user.getRole().equalsIgnoreCase("ADMIN")) return "Manage";
		return "ERROR";
	}

	
	public void setAverageStar(Model model, Integer id) {
		List<Comment> comments = commentService.getCommentByBook_id(id);
		int totalStar = 0;
		for (Comment comment : comments) {
			totalStar += comment.getStar();
		}
		model.addAttribute("comments", comments);
		if (comments.size() > 0) {
			model.addAttribute("averageStar", (double) Math.round(10 * totalStar / comments.size()) / 10);
		}
	}
	
	public String getAverageStar(Book book) {
		List<Comment> comments = commentService.getCommentByBook_id(book.getBook_id());
		int totalStar = 0;
		for (Comment comment : comments) {
			totalStar += comment.getStar();
		}
		if (comments.size() > 0) {
			return String.valueOf((double) Math.round(10 * totalStar / comments.size()) / 10) + "/5 ";
		}
		return "Chua ban";
	}
}
