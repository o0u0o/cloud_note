package com.aiuiot.cloud_note.dao;

import java.util.List;

import com.aiuiot.cloud_note.entity.Book;

public interface BookDao {

	/** 根据ID查询笔记本 */
	List<Book> findByUserId(String userId);

	/**
	 * <h2>保存笔记本</h2>
	 * @param book
	 */
	void save(Book book);

	/**
	 * <h2>删除笔记本</h2>
	 * @param bookId 笔记本ID
	 */
	void remove(Long bookId);
}
