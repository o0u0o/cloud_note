package com.aiuiot.cloud_note.controller;

import javax.annotation.Resource;

import com.aiuiot.cloud_note.entity.Note;
import org.springframework.web.bind.annotation.RequestMapping;
import com.aiuiot.cloud_note.service.NoteService;
import com.aiuiot.cloud_note.util.NoteResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 笔记Controller
 * @author aiuiot
 */
@RestController
@RequestMapping("/note")
public class NoteController {

	@Resource
	private NoteService noteService;

	/**
	 *
	 * @param title 笔记标题
	 * @param userId 用户ID
	 * @param bookId 笔记本ID
	 * @return
	 */
	@RequestMapping("/add.do")
	public NoteResult<Note> add(String title, String userId, String bookId){
		NoteResult<Note> result = noteService.addNote(title, userId, bookId);
		return result;
	}

	/**
	 * 加载笔记本列表
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/loadnotes.do")
	public NoteResult<List<Map>> loadNotes(String bookId) {
		NoteResult<List<Map>> result = noteService.LoadBookNotes(bookId);
		return result;
	}

	/**
	 * 加载笔记本信息
	 * @param noteId
	 * @return
	 */
	@RequestMapping("/load.do")
	public NoteResult<Note> loadNote(String noteId){
		NoteResult<Note> result = noteService.loadNote(noteId);
		return result;
	}

	/**
	 * 更新笔记
	 * @param noteId 笔记ID
	 * @param title 笔记标题
	 * @param body
	 * @return
	 */
	@RequestMapping("/update.do")
	public NoteResult<Object> update(String noteId, String title, String body) {
		NoteResult<Object> result = noteService.updateNote(noteId, title, body);
		System.out.println("1该方法执行了 "+result.getStatus());
		return result;
	}

	/**
	 * 删除笔记
	 * @param noteId 笔记ID
	 * @return
	 */
	@RequestMapping("delete.do")
	public NoteResult<Object> delete(String noteId){
		NoteResult<Object> result = noteService.deleteNote(noteId);
		return result;
	}


}
