package com.aiuiot.cloud_note.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.aiuiot.cloud_note.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aiuiot.cloud_note.dao.NoteDao;
import com.aiuiot.cloud_note.entity.Note;
import com.aiuiot.cloud_note.util.NoteResult;
import com.aiuiot.cloud_note.util.NoteUtil;

@Service("noteService")	//添加@Service纳入Spring容器管理
public class NoteServiceImpl implements NoteService {

	@Resource	//注入NoteDao对象（告诉Spring，这里要用到一个dao）
	private NoteDao noteDao;
	
	/**
	 * 根据userId查询笔记列表
	 */
	public NoteResult<List<Map>> LoadBookNotes(String userId) {
		//构建返回结果，后面使用
		NoteResult<List<Map>> result = new NoteResult<List<Map>>();
		//调用Dao得方法得到结果
		List<Map> list = noteDao.findByBookId(userId);
		
//		if(list.isEmpty()) {
//			result.setStatus(1);
//			result.setMsg("加载笔记错误");
//			return result;
//		}
		
		result.setStatus(0);
		result.setMsg("加载笔记成功");
		result.setData(list);
		return result;
	}

	
	public NoteResult<Note> loadNote(String noteId) {
		NoteResult<Note> result = new NoteResult<Note>();
		Note note = noteDao.findByNoteId(noteId);
		if(note == null) {
			result.setStatus(1);
			result.setMsg("未找到数据！");
			return result;
		}else {
			result.setStatus(0);
			result.setMsg("加载笔记信息成功");
			result.setData(note);			
			return result;
		}
	}

	@Transactional	//用户管理事务 
	public NoteResult<Object> updateNote(String noteId, String title, String body) {
		//构建NoteResult
		NoteResult<Object> result = new NoteResult<Object>();
		//创建note参数
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(title);
		note.setCn_note_body(body);
		Long time = System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		//更新数据库记录(返回rows，影响多少行？)
		int rows = noteDao.updateNote(note);
		if(rows == 1) {	//如果影响了一行记录
			result.setStatus(0);
			result.setMsg("保存笔记成功");
			return result;
		}else {
			result.setStatus(1);
			result.setMsg("保存笔记失败");
			return result;			
		}
		
	}


	public NoteResult<Note> addNote(String title, String userId, String bookId) {
		//构建结果
		NoteResult<Note> result = new NoteResult<Note>();
		
		//实例化note，并设置参数
		Note note = new Note();
		String statusId = "1";	//默认为1-正常 
		String noteId = NoteUtil.createId();	//使用uuid生成笔记ID
		long time = System.currentTimeMillis();	//创建事件
		
		note.setCn_user_id(userId);	//用户ID
		note.setCn_notebook_id(bookId);	//笔记本ID
		note.setCn_note_title(title);	//笔记标题
		
		note.setCn_note_id(noteId);	//笔记ID
		note.setCn_note_body("");	//笔记内容 为空
		note.setCn_note_create_time(time);	//最后修改事件
		note.setCn_note_last_modify_time(time);	//创建事件
		note.setCn_note_status_id(statusId);	//状态：1-normal 2-delete 3-
		note.setCn_note_type_id("1");	//类型1-normal 2-favor（收藏） 3-share（分享）
	
		noteDao.save(note);
		result.setStatus(0);
		result.setMsg("新增笔记成功");
		result.setData(note);
		return result;
	}


	public NoteResult<Object> deleteNote(String noteId) {
		//创建result
		NoteResult<Object> result = new NoteResult<Object>();
		int row = noteDao.delete(noteId);
		if(row == 1) {
			result.setStatus(0);
			result.setMsg("删除笔记成功!");
			return result;
		}else {
			result.setStatus(1);
			result.setMsg("更新笔记成功!");
			return result;
		}
	}

	@Transactional //加上事务管理注解
	public void deleteNotes(String... ids) {
		//String... 就是 String[]
		for (String id : ids) {
			int row = noteDao.deleteNote(id);
			System.out.println("row:"+row);
			if(row != 1) {
				//抛出异常，触发事务的回滚！
				throw new RuntimeException("删除出错");
			}
		}
	}

}
