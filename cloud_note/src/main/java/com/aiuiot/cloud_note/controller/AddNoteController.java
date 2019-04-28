package com.aiuiot.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiuiot.cloud_note.entity.Note;
import com.aiuiot.cloud_note.service.NoteService;
import com.aiuiot.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class AddNoteController {
	@Resource
	private NoteService service;
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Note> execute(String title, String userId, String bookId){
		NoteResult<Note> result = service.addNote(title, userId, bookId);
		return result;
	}
}
