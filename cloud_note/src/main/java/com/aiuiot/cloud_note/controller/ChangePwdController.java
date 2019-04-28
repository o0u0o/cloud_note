package com.aiuiot.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiuiot.cloud_note.service.UserService;
import com.aiuiot.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/user")
public class ChangePwdController {
	
	@Resource
	private UserService service;
	
	@RequestMapping("/changepwd.do")
	@ResponseBody
	public NoteResult<Object> execute(String userId, String last_password, String new_password){
		NoteResult<Object> result = service.modifyPassword(userId, last_password, new_password);
		System.out.println("ChangePwdController执行了");
		return result;
	}
	
}
