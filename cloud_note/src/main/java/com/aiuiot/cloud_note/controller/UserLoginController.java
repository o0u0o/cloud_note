package com.aiuiot.cloud_note.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiuiot.cloud_note.entity.User;
import com.aiuiot.cloud_note.service.UserService;
import com.aiuiot.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/user")			//配置请求路径
public class UserLoginController {
	
	@Resource
	private UserService userservice;
	
	@RequestMapping("/login.do")	//匹配具体请求
	@ResponseBody					//调用Jackson 实现JSON输出
	public NoteResult<User> execute(String name, String password) {
		System.out.println(name+" "+password);
		
		//调用userService处理登录请求
		NoteResult<User> result = userservice.checkLogin(name, password);
		
		return result;
	}

}
