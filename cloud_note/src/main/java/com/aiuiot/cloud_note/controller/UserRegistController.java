package com.aiuiot.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiuiot.cloud_note.service.UserService;
import com.aiuiot.cloud_note.util.NoteResult;

@Controller		//通过该标记让Spring管理起来
@RequestMapping("/user")	//匹配请求路径
public class UserRegistController {
	
	@Resource	//声明userService对象
	private UserService userService;
	
	@RequestMapping("add.do")	//匹配具体路径
	@ResponseBody
	public NoteResult<Object> execute(String name, String password, String nick){
		NoteResult<Object> result = userService.addUser(name, password, nick);
		return result;
	}
}
