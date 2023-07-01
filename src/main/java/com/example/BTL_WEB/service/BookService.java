package com.example.BTL_WEB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BTL_WEB.book.Book;
import com.example.BTL_WEB.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository repo;
	
	public List<Book> getAll(){
		return repo.findAll();
	}
	
	public Book getByID(Integer id) {
		return repo.findById(id).get();
	}

	public void deleteBook(Integer id) {
		repo.delete(repo.findById(id).get());
	}
	
	public void saveBook(Book book) {
		repo.save(book);
	}
}
