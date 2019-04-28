package com.aiuiot.cloud_note.dao;

import java.util.List;

import com.aiuiot.cloud_note.entity.Book;

public interface BookDao {
	public List<Book> findByUserId(String userId);	//根据ID查询
	
	public void save(Book book);	//保存笔记本
}
