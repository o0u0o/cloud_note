package test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.aiuiot.cloud_note.entity.Book;
import com.aiuiot.cloud_note.service.BookService;
import com.aiuiot.cloud_note.util.NoteResult;

import test.TestBase;

public class TestNotebookService extends TestBase{
	private BookService service;
	
	@Before
	public void init() {
		//通过调用super的getContext()方法获取bean
		service = super.getContext().getBean("notebookService",BookService.class);
	}
	
	@Test	//用例-1 预期结果：测试用户id存在的情况
	public void test1() {
		NoteResult<List<Book>> result = service.LoadUserBooks("52f9b276-38ee-447f-a3aa-0d54e7a736e4");
		System.out.println(result.getStatus()+" "+result.getMsg()+" "+result.getData());
	}
	
	@Test	//用例-2 预期结果：测试用户ID不存在的情况
	public void test2() {
		NoteResult<List<Book>> result = service.LoadUserBooks("xxx");
		System.out.println(result.getStatus()+" "+result.getMsg()+" "+result.getData());
	}
	
	@Test //用例-3 测试新增笔记本
	public void test3() {
		String userId = "6f2744d6b5bc4692ad01217b75c3d034";
		String bookName = "读书笔记";
		NoteResult<Object> result = service.addBook(userId, bookName);
		System.out.println(result.getStatus()+" "+result.getMsg());
	}
}
