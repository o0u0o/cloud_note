package test.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.aiuiot.cloud_note.dao.NoteDao;
import com.aiuiot.cloud_note.entity.Note;
import com.aiuiot.cloud_note.util.NoteUtil;

import test.TestBase;

public class TestNoteDao extends TestBase{
	private NoteDao noteDao;
	
	@Before
	public void init() {
		noteDao = super.getContext().getBean("noteDao",NoteDao.class);
	}
	
	@Test	//测试用例-1 测试正常bookId 预期：输出相关笔记信息
	public void test1() {
		List<Map> list = noteDao.findByBookId("516f6f4f-eaa3-4c76-84ff-530b92c7f64d");
		
		if(list.isEmpty()) {
			System.out.println("没有数据");
		}else {
			for (Map map : list) {
				//根据Map的key查询对于的value
				System.out.println(map.get("cn_note_id")+" "+map.get("cn_note_title"));
			}
		}
	}
	
	@Test	//测试用例-2 测试bookId为错误的情况 
	public void test2() {
		List<Map> list = noteDao.findByBookId("sdsds");
		if(list.isEmpty()){
			System.out.println("没有数据");
		}else {
			for (Map map : list) {
				System.out.println(map.get("cn_note_id")+" "+map.get("cn_note_id"));
			}
		}
	}
	
	@Test	//测试用例-3 测试findByNoteId
	public void test3() {
		Note note = noteDao.findByNoteId("051538a6-0f8e-472c-8765-251a795bc88f");
		System.out.println("ID:"+note.getCn_note_id());
		System.out.println("状态:"+note.getCn_note_status_id());
		System.out.println("标题:"+note.getCn_note_title());
		System.out.println("内容:"+note.getCn_note_body());
		
	}
	
	@Test	//测试用例-4 测试
	public void test4() {
		Note note = new Note();
		//note.setCn_note_id("051538a6-0f8e-472c-8765-251a795bc88f");
		String noteId = "051538a6-0f8e-472c-8765-251a795bc88f";
		String title = "《是他改变了中国》读后感";
		String body ="泪目！泪目啊！";
		Long time = System.currentTimeMillis();
		note.setCn_note_id(noteId);
		note.setCn_note_title(title);
		note.setCn_note_body(body);
		note.setCn_note_last_modify_time(time);
		int num = noteDao.updateNote(note);
		System.out.println("影响行数:"+num);
	}
	
	@Test
	public void tes5() {
		String userId = "0c9f285aa2ea47238aea1312e0f2e747";
		String title = "HTML+CSS";
		String bookId = "478543a4672147b8ab11d825292abf2d";
		String statusId = "1";
		String id = NoteUtil.createId();
		Note note = new Note();
		
		note.setCn_note_id(id);
		note.setCn_notebook_id(bookId);
		note.setCn_user_id(userId);
		note.setCn_note_status_id(statusId);
		note.setCn_note_title(title);
		
		noteDao.save(note);
		System.out.println(note);
	}
	
	@Test	//测试用例-5 测试删除笔记
	public void test6() {
		String noteId = "051538a6-0f8e-472c-8765-251a795bc88f";
		noteDao.delete(noteId);
		System.out.println("删除成功!");
	}
	
	@Test
	public void testUpdateNoteByMap() {
		String noteId = "ad44ea57bdba4241babe4c3ff2de1765";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "Java编程思想");
		map.put("body", "内容");
		map.put("noteId", noteId);
		//故意省略参数 body 和 time
		noteDao.updateNoteByMap(map);
	}
	
	@Test	//测试删除多条记录
	public void testDeleteNotes() {
		Map<String, Object> map = new HashMap<String, Object>();
		//模拟ID数据
		String[] ids = {"ab2ef5176c76442492eb9e6a09ad3214", "a5846b067c6649f095ce9d55b2fea91b"};
		map.put("ids", ids);
		map.put("status", 2);	//状态2为模拟删除，
		int row = noteDao.deleteNotes(map);
		System.out.println(row);
	}
	
}
