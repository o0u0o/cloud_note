package test.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aiuiot.cloud_note.dao.BookDao;
import com.aiuiot.cloud_note.entity.Book;

import test.TestBase;

public class TestBookDao extends TestBase{
	String[] conf = {"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
	ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
	BookDao notebookDao = ctx.getBean("bookDao",BookDao.class);
	
	@Test	//测试用例-1 测试根据用户ID查找笔记本
	public void test1() {
		
		List<Book> notebook = notebookDao.findByUserId("52f9b276-38ee-447f-a3aa-0d54e7a736e4");
		
		if(notebook != null) {
			for (Book n : notebook) {
				//System.out.println(n.getCn_notebook_id()+" "+n.getCn_user_id());
				String notebook_id = n.getCn_notebook_id();
				String user_id = n.getCn_user_id();
				String notebook_type_id = n.getCn_notebook_type_id();
				String notebook_name = n.getCn_notebook_name();
				String notebook_desc = n.getCn_notebook_desc();
				Timestamp notebook_createtime = n.getCn_notebook_createtime();
				System.out.println(notebook_id+" "+user_id+" "+notebook_type_id+" "+notebook_name+" "+notebook_desc+" "+notebook_createtime);
			}
		}else {
			System.out.println("用户不存在");
		}
	}
	
	@Test	//测试用例-2 测试新增笔记本
	public void test2() {
		String bookId = "0000015c-09fe-4eaa-aeb5-0000000";
		String userId = "0c9f285aa2ea47238aea1312e0f2e747";
		String bookName = "回忆录";

		Book book = new Book();
		book.setCn_notebook_id(bookId);
		book.setCn_user_id(userId);
		book.setCn_notebook_name(bookName);
		
		notebookDao.save(book);
		
		System.out.println(book);
	}
}
