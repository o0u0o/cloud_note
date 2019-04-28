package com.aiuiot.cloud_note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiuiot.cloud_note.service.NoteService;
import com.aiuiot.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class LoadNotesController {
	@Resource
	private NoteService service;
	
	//加载笔记本列表
		@RequestMapping("/loadnotes.do")
		@ResponseBody
		public NoteResult<List<Map>> executeLoadNotes(String bookId) {
			NoteResult<List<Map>> result = service.LoadBookNotes(bookId);
			return result;
		}

}
