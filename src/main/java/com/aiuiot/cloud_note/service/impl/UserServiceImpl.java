package com.aiuiot.cloud_note.service.impl;

import javax.annotation.Resource;

import com.aiuiot.cloud_note.common.enums.ResponseEnum;
import com.aiuiot.cloud_note.common.utils.IdUtils;
import com.aiuiot.cloud_note.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aiuiot.cloud_note.dao.UserDao;
import com.aiuiot.cloud_note.entity.User;
import com.aiuiot.cloud_note.common.utils.NoteResult;
import com.aiuiot.cloud_note.common.utils.NoteUtil;

@Service("userService")	//扫描的Spring容器
@Transactional
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	
	/**
	 * 模块功能-1: 用户登录
	 */
	//实现checkLogin方法
	@Override
	public NoteResult<User> checkLogin(String name, String password) {
		//按照参数name查询数据库
		User user = userDao.findByName(name);
		
		//检查用户名 如果user为空
		if(user == null) {
			return NoteResult.error(ResponseEnum.USERNAME_NOT_EXIST);
		}
		
		//检查密码:使用md5加密密码
		String md5Password = NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(md5Password)) {
			return NoteResult.error(ResponseEnum.PASSWORD_ERROR);
		}
		
		//用户名和密码都是正确的情况下
		return NoteResult.success(user, "登录成功");
	}

	/**
	 * 注册用户
	 *
	 */
	@Override
	public NoteResult<Object> addUser(String name, String password, String nick) {

		//用户检测 
		User hasUser = userDao.findByName(name);	//根据name查询用户数据

		//如果用户存在（不为空）
		if(hasUser != null) {
			return NoteResult.error(ResponseEnum.USERNAME_EXIST);
		}
		
		//添加用户
		User user = new User();		//创建一个user对象
		Long userId = IdUtils.getId();		//生成ID
		System.out.println("生成的ID:"+NoteUtil.md5(String.valueOf(userId)));
		String md5Password = NoteUtil.md5(password);	//使用MD5加密密码
		
		//给user的属性赋值
		user.setCn_user_id(userId);	//使用uuid配合MD5生成用户ID并设置
		user.setCn_user_name(name);	//设置用户名
		user.setCn_user_password(md5Password);	//设置密码
		user.setCn_user_nick(nick);	//设置昵称
		//newUser.setCn_user_token(u.getCn_user_token());
		
		userDao.save(user);	//调用save方法保存user信息（插入数据）

		return NoteResult.successByMsg("注册成功");
	}


	/**
	 * <h2>修改密码</h2>
	 * @param userId
	 * @param last_password
	 * @param new_password
	 * @return
	 */
	@Override
	public NoteResult<Object> modifyPassword(String userId, String last_password, String new_password) {
		User user = userDao.findByUserId(userId);
		System.out.println(user);
		String password = user.getCn_user_password();
		//加密后的新密码
		String md5New_password = NoteUtil.md5(new_password);
		//加密后的旧密码（用户输入的）
		String md5Last_password = NoteUtil.md5(last_password);
		
		if(password.equals(md5Last_password)) {
			user.setCn_user_password(md5New_password);
			userDao.updatePassword(user);
			return NoteResult.success("更新密码成功!");
		}

		return NoteResult.error(ResponseEnum.OLD_PASSWORD_ERROR);
	}

}
