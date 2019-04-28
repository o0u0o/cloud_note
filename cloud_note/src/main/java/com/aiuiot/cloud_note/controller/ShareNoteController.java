package com.aiuiot.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiuiot.cloud_note.entity.Share;
import com.aiuiot.cloud_note.service.ShareService;
import com.aiuiot.cloud_note.util.NoteResult;
import com.aiuiot.cloud_note.util.ShareResult;

@Controller
@RequestMapping("/share")
public class ShareNoteController {
	@Resource
	private ShareService shareService;
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId){
		NoteResult<Object> result = shareService.shareNote(noteId);
		return result;
	}
	
	
}
