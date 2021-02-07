package com.aiuiot.cloud_note.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.aiuiot.cloud_note.entity.Note;
import com.aiuiot.cloud_note.util.NoteResult;

@Service
public interface NoteService {
	public NoteResult<List<Map>> LoadBookNotes(String bookId);	//根据bookId加载笔记
	
	public NoteResult<Note> loadNote(String noteId);	//根据noteId加载笔记
	
	public NoteResult<Object> updateNote(String noteId, String title, String body);	//更新数据
	
	public NoteResult<Note> addNote(String title, String userId, String bookId);	//添加笔记列表
	
	public NoteResult<Object> deleteNote(String noteId);	//删除笔记
	
	//String... 动态参数，就是String[] 数组  "..."等同于"[]"
	public void deleteNotes(String... ids);
}
