package com.aiuiot.cloud_note.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.aiuiot.cloud_note.common.enums.ResponseEnum;
import com.aiuiot.cloud_note.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aiuiot.cloud_note.dao.NoteDao;
import com.aiuiot.cloud_note.entity.Note;
import com.aiuiot.cloud_note.common.utils.NoteResult;
import com.aiuiot.cloud_note.common.utils.NoteUtil;

@Service("noteService")	//添加@Service纳入Spring容器管理
public class NoteServiceImpl implements NoteService {

	@Resource	//注入NoteDao对象（告诉Spring，这里要用到一个dao）
	private NoteDao noteDao;
	
	/**
	 * 根据userId查询笔记列表
	 */
	@Override
	public NoteResult<List<Map>> LoadBookNotes(String userId) {
		//调用Dao得方法得到结果
		List<Map> list = noteDao.findByBookId(userId);
		
//		if(list.isEmpty()) {
//			result.setStatus(1);
//			result.setMsg("加载笔记错误");
//			return result;
//		}

		return NoteResult.success(list, "加载笔记成功");
	}

	
	@Override
	public NoteResult<Note> loadNote(String noteId) {
		Note note = noteDao.findByNoteId(noteId);
		if(note == null) {
			return NoteResult.error(ResponseEnum.ERROR, "未找到数据！");
		}

		return NoteResult.success(note, "加载笔记信息成功");
	}

	@Override
	@Transactional	//用户管理事务
	public NoteResult<Object> updateNote(String noteId, String title, String body) {
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
			return NoteResult.successByMsg("保存笔记成功");
		}else {
			return NoteResult.error(ResponseEnum.ERROR, "保存笔记失败");
		}
		
	}


	@Override
	public NoteResult<Note> addNote(String title, String userId, String bookId) {
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

		return NoteResult.success(note, "新增笔记成功");
	}


	@Override
	public NoteResult<Object> deleteNote(String noteId) {
		int row = noteDao.delete(noteId);
		if(row == 1) {
			return NoteResult.success("删除笔记成功!");
		}

		return NoteResult.successByMsg("更新笔记成功!");
	}

	@Transactional //加上事务管理注解
	@Override
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
