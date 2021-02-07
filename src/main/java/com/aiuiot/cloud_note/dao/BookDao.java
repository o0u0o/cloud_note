package com.aiuiot.cloud_note.dao;

import java.util.List;

import com.aiuiot.cloud_note.entity.Book;

public interface BookDao {

	/** 根据ID查询笔记本 */
	public List<Book> findByUserId(String userId);

	/** 保存笔记本 */
	public void save(Book book);
}
