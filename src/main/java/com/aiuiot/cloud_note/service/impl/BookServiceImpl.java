package com.aiuiot.cloud_note.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.aiuiot.cloud_note.common.enums.ResponseEnum;
import com.aiuiot.cloud_note.common.utils.IdUtils;
import com.aiuiot.cloud_note.service.BookService;
import org.springframework.stereotype.Service;

import com.aiuiot.cloud_note.dao.BookDao;
import com.aiuiot.cloud_note.entity.Book;
import com.aiuiot.cloud_note.common.utils.NoteResult;
import com.aiuiot.cloud_note.common.utils.NoteUtil;

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

		//调用方法，得到结果
		List<Book> notebooks = notebookDao.findByUserId(userId);

		//判断结果是否为空
		if(notebooks.isEmpty()) {
			return NoteResult.error(ResponseEnum.PASSWORD_ERROR, "加载用户笔记本错误");
		}

		return NoteResult.success(notebooks, "加载笔记本成功");
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

		//新增一个笔记本
		Book book = new Book();
		String bookId = IdUtils.getIdStr();
		//给属性赋值
		book.setCn_user_id(userId);
		book.setCn_notebook_name(bookName);
		book.setCn_notebook_id(bookId);
		notebookDao.save(book);

		return NoteResult.successByMsg("新增笔记本成功");
	}

}
