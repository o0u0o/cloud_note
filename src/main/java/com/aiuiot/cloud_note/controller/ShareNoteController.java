package com.aiuiot.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;

import com.aiuiot.cloud_note.service.ShareService;
import com.aiuiot.cloud_note.common.utils.NoteResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/share")
public class ShareNoteController {
	@Resource
	private ShareService shareService;
	
	@RequestMapping("/add.do")
	public NoteResult<Object> add(String noteId){
		NoteResult<Object> result = shareService.shareNote(noteId);
		return result;
	}
	
	
}
