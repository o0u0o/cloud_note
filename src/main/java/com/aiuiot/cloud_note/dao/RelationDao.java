package com.aiuiot.cloud_note.dao;

import java.util.List;

import com.aiuiot.cloud_note.entity.Book;
import com.aiuiot.cloud_note.entity.User;

public interface RelationDao {
	
	//查询用户和笔记本信息(关联过个对象)
	public User findUserAndBooks(String userId);
	
	public User findUserAndBooks1(String userId);
	
	//关联单个对象-通过查询笔记信息，关联用户信息（两条SQL语句）
	public List<Book> findBookAndUser();
	
	//关联单个对象-通过查询笔记信息，关联用户信息（一条SQL语句）
	public List<Book> findBookAndUser1();
	
}
