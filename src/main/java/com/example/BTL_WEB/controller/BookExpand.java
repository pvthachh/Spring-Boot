package com.example.BTL_WEB.controller;

import com.example.BTL_WEB.book.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookExpand {
	private Book book;
	private String avgStar;
}
