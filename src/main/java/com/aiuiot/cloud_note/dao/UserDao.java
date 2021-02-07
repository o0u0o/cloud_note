package com.aiuiot.cloud_note.dao;


import com.aiuiot.cloud_note.entity.User;

public interface UserDao {
	
	//根据user_id查询(登录方法)
	public User findByName(String name);	
	
	//保存user（注册方法）
	public void save(User user);
	
	//根据userId查询	
	public User findByUserId(String userId);
	
	//根据userId更新密码
	public int updatePassword(User user);
	
}
