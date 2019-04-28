package com.aiuiot.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiuiot.cloud_note.service.NoteService;
import com.aiuiot.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class UpdateNoteController {
	@Resource
	private NoteService service;
	
	@RequestMapping("/update.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId, String title, String body) {
		NoteResult<Object> result = service.updateNote(noteId, title, body);
		System.out.println("1该方法执行了 "+result.getStatus());
		return result;
	}
}
