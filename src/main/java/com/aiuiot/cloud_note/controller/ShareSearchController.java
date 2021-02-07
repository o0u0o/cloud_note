package com.aiuiot.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.aiuiot.cloud_note.entity.Share;
import com.aiuiot.cloud_note.service.ShareService;
import com.aiuiot.cloud_note.common.utils.NoteResult;

@Controller
@RequestMapping("/share")
public class ShareSearchController {
	
	@Resource
	private ShareService shareService;
	
	@RequestMapping("/search.do")
	public NoteResult<List<Share>> search(String keyword, int page){
		System.out.println("ShareSearchController方法执行了");
		System.out.println("keyword:"+keyword);
		return shareService.searchNote(keyword, page);
	}
	
}
