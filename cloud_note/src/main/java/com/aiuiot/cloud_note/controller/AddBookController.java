package com.aiuiot.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiuiot.cloud_note.service.BookService;
import com.aiuiot.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")
public class AddBookController {
	
	@Resource
	private BookService service;
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Object> execute(String userId, String bookName){
		NoteResult<Object> result = service.addBook(userId, bookName);
		//System.out.println("新增笔记本执行了");
		return result;
	}
}
