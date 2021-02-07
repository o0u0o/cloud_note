package com.aiuiot.cloud_note.controller;


import com.aiuiot.cloud_note.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.aiuiot.cloud_note.service.BookService;
import com.aiuiot.cloud_note.util.NoteResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")

public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/add.do")
	public NoteResult<Object> add(String userId, String bookName){
		NoteResult<Object> result = bookService.addBook(userId, bookName);
		return result;
	}

	@RequestMapping("/loadBooks.do")
	public NoteResult<List<Book>> loadBooks(String userId){
		NoteResult<List<Book>> result = bookService.LoadUserBooks(userId);
		return result;
	}
}
