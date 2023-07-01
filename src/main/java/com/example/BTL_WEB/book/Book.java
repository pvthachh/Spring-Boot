package com.example.BTL_WEB.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Integer book_id;

	@Column(name = "title")
	private String title;

	@Column(name = "author")
	private String author;

	@Column(name = "image")
	private String image;

	@Column(name = "description")
	private String description;

	@Column(name = "publish")
	private String publish;

	@Column(name = "price")
	private Integer price;

	@Column(name = "page")
	private Integer page;

	@Column(name = "sold")
	private Integer sold;
	
	@Column(name = "category")
	private String category;
}
