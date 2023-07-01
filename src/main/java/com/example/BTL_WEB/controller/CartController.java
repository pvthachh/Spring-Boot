package com.example.BTL_WEB.controller;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BTL_WEB.book.Book;
import com.example.BTL_WEB.book.Order_detail;
import com.example.BTL_WEB.book.User;
import com.example.BTL_WEB.service.BookService;
import com.example.BTL_WEB.service.Order_detailService;

@Controller
public class CartController {
	@Autowired
	private BookService bookService;
	@Autowired
	private Order_detailService order_detailService;
	
	@PostMapping("/save/order-detail")
	public String saveOrderDetail(@RequestParam("book_id") Integer book_id,
			@RequestParam("quantity") int quantity, HttpSession session) {
		Book book = bookService.getByID(book_id);
		book.setSold(book.getSold() + quantity);
		bookService.saveBook(book);
		
		int total = book.getPrice() * quantity;
		User user = (User) session.getAttribute("userLogin");
		Order_detail order_detail = new Order_detail();
		order_detail.setUser(user);
		order_detail.setBook(book);
		order_detail.setQuantity(quantity);
		order_detail.setTotal(total);
		
		Order_detail order_detail2 = order_detailService.getOrderByUserIDAndBookID(user.getUser_id(), book_id);
		if(order_detail2 != null) {
			order_detail2.setQuantity(quantity);
			order_detail2.setTotal(total);
			order_detailService.save(order_detail2);
			return "redirect:/detail/" + String.valueOf(book_id);
		}
		System.err.println(order_detail);
		order_detailService.save(order_detail);
		return "redirect:/detail/" + String.valueOf(book_id);
	}
	
	@PostMapping("/save/order-detail-1")
	public String saveOrderDetailHome(@RequestParam("book_id") Integer book_id,
			@RequestParam("quantity") int quantity, HttpSession session) {
		Book book = bookService.getByID(book_id);
		book.setSold(book.getSold() + quantity);
		bookService.saveBook(book); 
		
		int total = book.getPrice() * quantity;
		User user = (User) session.getAttribute("userLogin");
		Order_detail order_detail = new Order_detail();
		order_detail.setUser(user);
		order_detail.setBook(book);
		order_detail.setQuantity(quantity);
		order_detail.setTotal(total);
		
		Order_detail order_detail2 = order_detailService.getOrderByUserIDAndBookID(user.getUser_id(), book_id);
		if(order_detail2 != null) {
			order_detail2.setQuantity(quantity);
			order_detail2.setTotal(total);
			order_detailService.save(order_detail2);
			return "redirect:/cart";
		}
		System.err.println(order_detail);
		order_detailService.save(order_detail);
		return "redirect:/cart";
	}
	
	@GetMapping("/cart")
	public String goToCart(HttpSession session, Model model) {
		
		User user = (User) session.getAttribute("userLogin");
		if(user != null) {
			List<Order_detail> order_details = order_detailService.getOrderByUserID(user.getUser_id());
			int totalCost = 0;
			for(Order_detail o: order_details) {
				totalCost += o.getTotal();
			}
			Format decimalFormat = new DecimalFormat("#,###,###,###");
	        String formattedTotalCost = decimalFormat.format(totalCost);
			model.addAttribute("totalCost", formattedTotalCost);
			model.addAttribute("carts", order_details);
			return "Cart";
		}
		return "ERROR";
	}
	
	@DeleteMapping("/cart/delete")
	public String deleteCart(@RequestParam("book_id") Integer book_id, HttpSession session) {
		User user = (User) session.getAttribute("userLogin");
		int quantity = order_detailService.getOrderByUserIDAndBookID(user.getUser_id(), book_id).getQuantity();
		Book book = new Book();
		book = bookService.getByID(book_id);
		book.setSold(book.getSold() - quantity);
		bookService.saveBook(book);
		
		if(user != null) {
			order_detailService.deleteByUserIdAndBookId(user.getUser_id(), book_id);
			return "redirect:/cart";
		}
		return "ERROR";
	}
}
