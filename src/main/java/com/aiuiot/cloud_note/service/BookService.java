package com.aiuiot.cloud_note.service;

import java.util.List;

import com.aiuiot.cloud_note.entity.Book;
import com.aiuiot.cloud_note.common.utils.NoteResult;

public interface BookService {
	public NoteResult<List<Book>> LoadUserBooks(String userId);

	/**
	 * 新增笔记本
	 * @param userId
	 * @param bookName
	 * @return
	 */
	NoteResult<Object> addBook(String userId, String bookName);	//新增笔记本

	/**
	 * <h2>删除笔记本</h2>
	 * @param bookId
	 * @return
	 */
	NoteResult<Object> delBook(Long bookId);
}
