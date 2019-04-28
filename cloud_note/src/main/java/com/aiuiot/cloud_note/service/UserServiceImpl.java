package com.aiuiot.cloud_note.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aiuiot.cloud_note.dao.UserDao;
import com.aiuiot.cloud_note.entity.User;
import com.aiuiot.cloud_note.util.NoteResult;
import com.aiuiot.cloud_note.util.NoteUtil;

@Service("userService")	//扫描的Spring容器
@Transactional
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	
	/**
	 * 模块功能-1: 用户登录
	 */
	//实现checkLogin方法
	public NoteResult<User> checkLogin(String name, String password) {
		//接收结果数据
		NoteResult<User> result = new NoteResult<User>();
		//按照参数name查询数据库
		User user = userDao.findByName(name);
		
		//检查用户名 如果user为空
		if(user == null) {
			result.setStatus(1);			//状态值 1代表用户不存在
			result.setMsg("用户名不存在");		//消息提示
			return result;					//返回result
		}
		
		//检查密码
		String md5Password = NoteUtil.md5(password);	//使用md5加密密码
		if(!user.getCn_user_password().equals(md5Password)) {
			result.setStatus(2);		//状态值2 密码错误
			result.setMsg("密码错误");	//消息提醒
			return result;
		}
		
		//用户名和密码都是正确的情况下
		result.setStatus(0);	//状态值0 登录成功
		result.setMsg("登录成功");	
		result.setData(user);	//将user对象设置到data
		
		return result;
	}

	/**
	 * 注册用户
	 */
	//实现addUser方法
	public NoteResult<Object> addUser(String name, String password, String nick) {
		//实例化result 用于接收结果数据
		NoteResult<Object> result = new NoteResult<Object>();
		
		//用户检测 
		User hasUser = userDao.findByName(name);	//根据name查询用户数据

		//如果用户存在（不为空）
		if(hasUser != null) {
			//定义提示信息 状态值为1 代表用户已被占用
			result.setStatus(1);
			result.setMsg("用户已被占有");
			return result;
		}
		
		//添加用户
		User user = new User();		//创建一个user对象
		String uuid = NoteUtil.createId();		//生成ID
		System.out.println("生成的ID:"+NoteUtil.md5(uuid));
		String md5Password = NoteUtil.md5(password);	//使用MD5加密密码
		
		//给user的属性赋值
		user.setCn_user_id(uuid);	//使用uuid配合MD5生成用户ID并设置
		user.setCn_user_name(name);	//设置用户名
		user.setCn_user_password(md5Password);	//设置密码
		user.setCn_user_nick(nick);	//设置昵称
		//newUser.setCn_user_token(u.getCn_user_token());
		
		userDao.save(user);	//调用save方法保存user信息（插入数据）
		//构建返回结果（定义提示信息） 状态值为0 代表用户注册成功
		result.setStatus(0);	//设置状态值为0
		result.setMsg("注册成功");	//设置消息提示
		return result;	//返回result
	}

	/**
	 * 修改密码
	 */
	public NoteResult<Object> modifyPassword(String userId, String last_password, String new_password) {
		NoteResult<Object> result = new NoteResult<Object>();
		User user = userDao.findByUserId(userId);
		System.out.println(user);
		String password = user.getCn_user_password();
		String md5New_password = NoteUtil.md5(new_password);	//加密后的新密码
		String md5Last_password = NoteUtil.md5(last_password);	//加密后的旧密码（用户输入的）
		
		if(password.equals(md5Last_password)) {
			user.setCn_user_password(md5New_password);
			userDao.updatePassword(user);
			result.setStatus(0);
			result.setMsg("更新密码成功!");
			return result;
		}else {
			result.setStatus(1);
			result.setMsg("原密码错误");
			return result;
		}
	}

}
