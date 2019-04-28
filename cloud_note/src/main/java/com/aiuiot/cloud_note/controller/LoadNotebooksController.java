package com.aiuiot.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiuiot.cloud_note.entity.Book;
import com.aiuiot.cloud_note.service.BookService;
import com.aiuiot.cloud_note.util.NoteResult;

/**
 * 
 * All rights Reserved, Designed By www.aiuiot.com
 * @Title:  LoadNotebooksController.java   
 * @Package com.aiuiot.cloud_note.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: ZerOneth 
 * @date:   2019年4月23日 上午11:30:40   
 * @version V1.0 
 * @Copyright: 2019 www.aiuiot.com Inc. All rights reserved. 
 * 注意：本内容仅限于xx公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Controller
@RequestMapping("/book")
public class LoadNotebooksController {
	
	@Resource
	private BookService notebookService;
	
	@RequestMapping("/loadBooks.do")
	@ResponseBody
	public NoteResult<List<Book>> execute(String userId){
		NoteResult<List<Book>> result = notebookService.LoadUserBooks(userId);
		return result;
	}
}
