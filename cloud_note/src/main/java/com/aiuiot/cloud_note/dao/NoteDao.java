package com.aiuiot.cloud_note.dao;

import java.util.List;
import java.util.Map;

import com.aiuiot.cloud_note.entity.Note;
import com.aiuiot.cloud_note.entity.Share;

public interface NoteDao {
	/**
	 * 
	 * 接收两个数据，可以使用Map类型来接收
	 * 可减少冗余数据
	 * @param bookId
	 * @return
	 */
	//public List<Note> findByBookId(String bookId);	//根据bookId查询
	
	public List<Map> findByBookId(String bookId);
	
	//根据笔记本ID查询笔记信息
	public Note findByNoteId(String noteId); 
	
	//更新信息,返回类型为int（影响行数）
	public int updateNote(Note note);	
	
	//更新信息,(动态SQL执行方式)
	public int updateNoteByMap(Map<String, Object> map);	
	
	//保存笔记
	public void save(Note note);	
	
	//删除笔记(将状态值修改为2 状态值：1-正常 2-删除)
	public int delete(String noteId);
	
	/**
	 * 批量删除笔记
	 * map 中需要添加两个参数:
	 * ids 代表被删除笔记的ID列表
	 * status 代表被删除笔记的状态属性
	 * map={ids:[id1,id2,id3...],status:2}
	 * @param map
	 * @return
	 */
	public int deleteNotes(Map<String, Object> map);
	
	//删除一个笔记
	public int deleteNote(String id);
	
}
