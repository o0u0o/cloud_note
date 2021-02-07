package com.aiuiot.cloud_note.controller;

import javax.annotation.Resource;

import com.aiuiot.cloud_note.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import com.aiuiot.cloud_note.service.UserService;
import com.aiuiot.cloud_note.util.NoteResult;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aiuiot
 * 注解 @RestController 通过该标记让Spring管理起来
 */
@RestController
@RequestMapping("/user")	//匹配请求路径
public class UserController {

	//声明userService对象
	@Resource
	private UserService userService;

	/**
	 * 用户注册
	 * @param name
	 * @param password
	 * @param nick
	 * @return
	 */
	@RequestMapping("add.do")
	public NoteResult<Object> add(String name, String password, String nick){
		NoteResult<Object> result = userService.addUser(name, password, nick);
		return result;
	}

	/**
	 * 用户登陆
	 * @param name	用户名
	 * @param password 密码
	 * 1、@RequestMapping("/login.do") 匹配具体请求
	 * @return
	 */
	@RequestMapping("/login.do")
	public NoteResult<User> login(String name, String password) {
		System.out.println(name+" "+password);
		//调用userService处理登录请求
		NoteResult<User> result = userService.checkLogin(name, password);
		return result;
	}

	/**
	 * 修改密码
	 * @param userId 用户ID
	 * @param last_password
	 * @param new_password
	 * @return
	 */
	@RequestMapping("/changepwd.do")
	public NoteResult<Object> changePwd(String userId, String last_password, String new_password){
		NoteResult<Object> result = userService.modifyPassword(userId, last_password, new_password);
		System.out.println("ChangePwdController执行了");
		return result;
	}
}
