package com.aiuiot.cloud_note.dao;


import com.aiuiot.cloud_note.entity.User;

public interface UserDao {

	/**
	 * <h2>根据user_id查询(登录方法)</h2>
	 */
	public User findByName(String name);

	/**
	 * <h2>保存user（注册方法）</h2>
	 */
	public void save(User user);

	/**
	 * <h2>根据userId查询</h2>
 	 * @param userId 用户ID
	 * @return
	 */
	public User findByUserId(String userId);

	/**
	 * <h2>根据userId更新密码</h2>
	 * @param user
	 * @return
	 */
	public int updatePassword(User user);
	
}
