package test.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.aiuiot.cloud_note.entity.Note;
import com.aiuiot.cloud_note.service.NoteService;
import com.aiuiot.cloud_note.util.NoteResult;

import test.TestBase;

public class TestNoteService extends TestBase{
	
	@Resource
	private NoteService service;
	
	@Before
	public void init() {
		service = super.getContext().getBean("noteService", NoteService.class);
	}
	
	@Test	//测试用例-1 测试NoteServlet的LoadBookNotes方法
	public void test1() {
		NoteResult<List<Map>> result = service.LoadBookNotes("516f6f4f-eaa3-4c76-84ff-530b92c7f64d");
		System.out.println(result.getStatus()+" "+result.getMsg()+" "+result.getData());	
	}
	
	@Test	//测试用例-2 测试NoteServlet的loadNote方法
	public void test2() {
		NoteResult<Note> result = service.loadNote("051538a6-0f8e-472c-8765-251a795bc88f");
		//System.out.println(result.get);
		System.out.println("状态:"+result.getStatus());
		System.out.println("信息:"+result.getMsg());
		System.out.println("数据:"+result.getData());
	}
	
	@Test	//测试用例-3 测试NoteServlet的updateNote方法
	public void test3() {
		String noteIderror = "0sdsdd0cdecc996";	//错误ID
		String noteId = "0a652205-c8af-41e0-986a-80d0cdecc996";
		String title = "《时间简史》读后感";
		String body = "读后感！。。。";
		NoteResult<Object> result = service.updateNote(noteId, title, body);
		System.out.println(result.getStatus()+" "+result.getMsg()+" "+result.getData());
	}
	
	@Test	//测试用例-4 测试addNote方法
	public void test4() {
		String title = "队列详解";
		String userId = "0c9f285aa2ea47238aea1312e0f2e747";
		String bookId = "94730716-2002-4952-ba23-aeb1e92ea00a";
		NoteResult<Note> result = service.addNote(title, userId, bookId);
		System.out.println(result.getStatus()+" "+result.getMsg());
	}
	
	@Test	//测试用例-5 测试deleteNote方法
	public void test5() {
		String noteId = "5545b00f9ae84802bfd1a2533845fa6c";
		NoteResult<Object> result = service.deleteNote(noteId);
		System.out.println(result.getStatus()+" "+result.getMsg());
	}
	
	@Test	//测试用例-6 
	public void testDeleteNotes() {
		//调用动态参数时候，可以不创建数组，直接写参数
		
		service.deleteNotes("769a1ff3833d4aaea92e5ac6f6ce537c","id1","id2");
	}
	
}
