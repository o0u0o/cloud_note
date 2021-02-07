package com.aiuiot.cloud_note.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.aiuiot.cloud_note.service.BookService;
import org.springframework.stereotype.Service;

import com.aiuiot.cloud_note.dao.BookDao;
import com.aiuiot.cloud_note.entity.Book;
import com.aiuiot.cloud_note.util.NoteResult;
import com.aiuiot.cloud_note.util.NoteUtil;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Resource	//注入dao的对象
	private BookDao notebookDao;

	/**
	 * 加载用户的笔记本
	 * @param userId
	 * @return
	 */
	@Override
	public NoteResult<List<Book>> LoadUserBooks(String userId) {
		
		//构建返回结果
		NoteResult<List<Book>> result = new NoteResult<List<Book>>();
		
		//调用方法，得到结果
		List<Book> notebooks = notebookDao.findByUserId(userId);
		//System.out.println(notebooks.isEmpty());

		//判断结果是否为空
		if(notebooks.isEmpty()) {
			result.setStatus(1);
			result.setMsg("加载用户笔记本错误");
			return result;	
		}
		
		result.setStatus(0);
		result.setMsg("加载笔记本成功");
		result.setData(notebooks);
		
		return result;
	}

	/**
	 * 新增笔记本
	 * @param userId
	 * @param bookName
	 * @return
	 */
	@Override
	public NoteResult<Object> addBook(String userId, String bookName) {
		//实例化result，用于接收结果集
		NoteResult<Object> result = new NoteResult<Object>();
		
		//新增一个笔记本
		Book book = new Book();
		String uuid = NoteUtil.createId();
		//给属性赋值
		book.setCn_user_id(userId);
		book.setCn_notebook_name(bookName);
		book.setCn_notebook_id(uuid);
		notebookDao.save(book);
		//构建返回结果
		result.setStatus(0);
		result.setMsg("新增笔记本成功");
		return result;
	}

}
