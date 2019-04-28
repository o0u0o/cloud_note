package com.aiuiot.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiuiot.cloud_note.entity.Share;
import com.aiuiot.cloud_note.service.ShareService;
import com.aiuiot.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/share")
public class ShareSearchController {
	
	@Resource
	private ShareService shareService;
	
	@RequestMapping("/search.do")
	@ResponseBody
	public NoteResult<List<Share>> execute(String keyword, int page){
		System.out.println("ShareSearchController方法执行了");
		System.out.println("keyword:"+keyword);
		NoteResult<List<Share>> result = shareService.searchNote(keyword, page);
		return result;
	}
	
}
