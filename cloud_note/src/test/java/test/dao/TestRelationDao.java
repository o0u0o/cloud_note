package test.dao;



import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.aiuiot.cloud_note.dao.RelationDao;
import com.aiuiot.cloud_note.entity.Book;
import com.aiuiot.cloud_note.entity.User;

import test.TestBase;

/**
 * 测试Mybatis关联映射
 * @author aiuiot
 *
 */
public class TestRelationDao extends TestBase{
	private RelationDao rdao;
	
	@Before
	public void init() {
		rdao = super.getContext().getBean("relationDao",RelationDao.class);
	}
	
	@Test //测试用例-1 测试两个SQL语句加载的情况
	public void test1() {
		String userId = "0c9f285aa2ea47238aea1312e0f2e747";
		User user = rdao.findUserAndBooks(userId);
		System.out.println("==========用户信息==========");
		System.out.println("==名字:"+user.getCn_user_name()+"==========");
		System.out.println("==用户昵称:"+user.getCn_user_nick()+"==========");
		System.out.println("==拥有笔记本数量:"+user.getBooks().size()+"==========");
		System.out.println("==========笔记本列表==========");
		for (Book book : user.getBooks()) {
			System.out.println(book.getCn_notebook_name());
		}
	}
	
	@Test //测试用例-2 测试案例一(关联多个对象)测试一个SQL语句加载
	public void test2() {
		String userId = "0c9f285aa2ea47238aea1312e0f2e747";
		User user = rdao.findUserAndBooks1(userId);
		System.out.println("==========用户信息==========");
		System.out.println("==名字:"+user.getCn_user_name()+"==========");
		System.out.println("==用户昵称:"+user.getCn_user_nick()+"==========");
		System.out.println("==拥有笔记本数量:"+user.getBooks().size()+"==========");
		System.out.println("==========笔记本列表==========");
		for (Book book : user.getBooks()) {
			System.out.println(book.getCn_notebook_name());
		}
	}
	
	@Test //测试用例-3 测试案例二(关联单个对象) 测试俩个SQL语句加载
	public void test3() {
		//查询所有的笔记，和关联的用户
		List<Book> list = rdao.findBookAndUser();
		for (Book book : list) {
			System.out.println("笔记名:"+book.getCn_notebook_name()+" 创建时间:"+book.getCn_notebook_createtime());
			if(book.getUser()!=null) {
				System.out.println("作者"+book.getUser().getCn_user_name());
			}
			System.out.println();
		}
	}
	
	@Test	//测试用例-4 测试案例二（）一个SQL语句加载
	public void test4() {
		List<Book> list = rdao.findBookAndUser1();
		System.out.println("该方法执行了");
		for (Book book : list) {
			System.out.println("笔记名:"+book.getCn_notebook_name()+" 创建时间:"+book.getCn_notebook_createtime());
			if(book.getUser()!=null) {
				System.out.println("作者:"+book.getUser().getCn_user_name());
			}
		}
	}
	
}
