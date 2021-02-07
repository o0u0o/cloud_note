package com.aiuiot.cloud_note.service;


import com.aiuiot.cloud_note.entity.User;
import com.aiuiot.cloud_note.common.utils.NoteResult;

public interface UserService {
	/**
	 * 检查登录
	 * @param name
	 * @param password
	 * @return
	 */
	public NoteResult<User> checkLogin(String name, String password);
	
	//添加用户
	public NoteResult<Object> addUser(String name, String password, String nick);	
	
	//修改密码
	public NoteResult<Object> modifyPassword(String userId, String last_password, String new_password);
}
