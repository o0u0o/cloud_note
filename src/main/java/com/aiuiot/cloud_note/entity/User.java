package com.aiuiot.cloud_note.entity;

import java.io.Serializable;
import java.util.List;
/**
 * 类属性名和属性数据类型与数据库cn_user字段名及数据类型保持一致
 * @author aiuiot
 *
 */
public class User implements Serializable {
	private Long cn_user_id;			//用户ID
	private String cn_user_name;		//用户名
	private String cn_user_password;	//密码
	private String cn_user_token;		//令牌
	private String cn_user_nick;		//昵称
	
	private List<Book> books;			//笔记本
	

	public Long getCn_user_id() {
		return cn_user_id;
	}

	public void setCn_user_id(Long cn_user_id) {
		this.cn_user_id = cn_user_id;
	}

	public String getCn_user_name() {
		return cn_user_name;
	}

	public void setCn_user_name(String cn_user_name) {
		this.cn_user_name = cn_user_name;
	}

	public String getCn_user_password() {
		return cn_user_password;
	}

	public void setCn_user_password(String cn_user_password) {
		this.cn_user_password = cn_user_password;
	}

	public String getCn_user_token() {
		return cn_user_token;
	}

	public void setCn_user_token(String cn_user_token) {
		this.cn_user_token = cn_user_token;
	}

	public String getCn_user_nick() {
		return cn_user_nick;
	}

	public void setCn_user_nick(String cn_user_nick) {
		this.cn_user_nick = cn_user_nick;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "User [cn_user_id=" + cn_user_id + ", cn_user_name=" + cn_user_name + ", cn_user_password="
				+ cn_user_password + ", cn_user_token=" + cn_user_token + ", cn_user_nick=" + cn_user_nick + ", books="
				+ books + "]";
	}
	

}
