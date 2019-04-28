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
public class LoadNoteController {
	@Resource
	private NoteService service;
	
	//加载笔记本信息
	@RequestMapping("/load.do")
	@ResponseBody
	public NoteResult<Note> executeLoadNote(String noteId){
		NoteResult<Note> result = service.loadNote(noteId);
		return result;
	}
}

