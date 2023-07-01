package com.example.BTL_WEB.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BTL_WEB.book.Book;
import com.example.BTL_WEB.book.User;
import com.example.BTL_WEB.service.BookService;
import com.example.BTL_WEB.service.CommentService;
import com.example.BTL_WEB.service.Order_detailService;
import com.example.BTL_WEB.service.UserService;

@Controller
public class AdminController {
	@Autowired
	private BookService bookService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private Order_detailService order_detailService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/manage/user")
	public String getAllUser(Model model, HttpSession session) {
		User user = (User) session.getAttribute("userLogin");
		if(user != null) {
			model.addAttribute("users", userService.getAllsExceptAdmin());
			return "Manage-user";
		}
		return "ERROR";
	}
	
	@GetMapping("/manage/book")
	public String getAllBook(Model model, HttpSession session) {
		User user = (User) session.getAttribute("userLogin");
		if(user != null) {
			model.addAttribute("books", bookService.getAll());
			return "Manage-book";
		}
		return "ERROR";
	}
	
	@PostMapping("/delete/user")
	public String deleteUser(@RequestParam("user_id") Integer user_id, HttpSession session) {
		User user = (User) session.getAttribute("userLogin");
		if(user != null) {
			commentService.deleteCommentByUserID(user_id);
			order_detailService.deleteByUser_id(user_id);
			userService.delete(user_id);
			return "redirect:/manage/user";
		}
		return "ERROR";
	}
	
	@PostMapping("/delete/book")
	public String deleteBook(@RequestParam("book_id") Integer book_id, HttpSession session) {
	 	User user = (User) session.getAttribute("userLogin");
		if(user != null) {
			commentService.deleteCommentByBookID(book_id);
			order_detailService.deleteByBook_id(book_id);
			bookService.deleteBook(book_id);
			return "redirect:/manage/book";
		}
		return "ERROR";
	}
	
	@PostMapping("/new-book")
	public String createBook(Book book, HttpSession session) {
	    User user = (User) session.getAttribute("userLogin");
	    if (user != null) {
	        if(book.getTitle() != null && book.getAuthor()!=null) {
	        	bookService.saveBook(book);
	        	return "redirect:/manage/book";
	        }
	        return "Add-book";
	    }
	    return "ERROR";
	}
	
	@PostMapping("/view-book")
	public String viewBook(Model model, @RequestParam("book_id") Integer book_id, HttpSession session) {
		User user = (User) session.getAttribute("userLogin");
		if(user != null) {
			model.addAttribute("book", bookService.getByID(book_id));
			return "View-book";
		}
		return "ERROR";
	}
	
	@PutMapping("/edit-book")
	public String updateBook(@RequestParam("book_id") Integer book_id, Book book, HttpSession session){
		User user = (User) session.getAttribute("userLogin");
		if(user != null) {
			bookService.saveBook(book);
			return "redirect:/manage/book";
		}
		return "ERROR";
	}
}