package test.service;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aiuiot.cloud_note.entity.User;
import com.aiuiot.cloud_note.service.UserService;
import com.aiuiot.cloud_note.common.utils.NoteResult;

public class TestUserService {
	private UserService service;
	
	//将实例化上下文对象抽取出来
	@Before
	public void init() {
		String[] files={"conf/spring-mybatis.xml","conf/spring-mvc.xml","conf/spring-transaction.xml"}; 
		ApplicationContext ctx = new ClassPathXmlApplicationContext(files);

		service = ctx.getBean("userService", UserService.class);
	}
	
	@Test	//用例-1：预期结果:测试用户名不存在的情况
	public void test1() {
		System.out.println(service.getClass().getName());//AOP动态代理测试
		NoteResult<User> result = service.checkLogin("刘翔", "liu123");
		System.out.println(result.getMsg()+" "+result.getStatus());
		
	}
	
	@Test	//用例-2:预期结果：测试用户名密码错误
	public void test2() {
		NoteResult<User> result = service.checkLogin("demo", "demo");
		System.out.println(result.getStatus()+" "+result.getMsg());
	}
	
	@Test	//用例-3:预期结果：用户名和密码都正确
	public void test3() {
		NoteResult<User> result = service.checkLogin("demo", "123456");
		System.out.println(result.getStatus()+" "+result.getMsg()+" "+result.getData());
	}
	
	@Test	//用例-4：预期结果：注册成功（数据库新增用户）
	public void test4() {
		String name = "yue";
		String password = "123456";
		String nick = "豫鄂";
		NoteResult<Object> result = service.addUser(name,password,nick);
		System.out.println(result.getStatus()+" "+result.getMsg());
	}
	
	@Test	//用例-5: 测试修改密码
	public void test5() {
		String userId = "0c9f285aa2ea47238aea1312e0f2e747";
		//String last_password = "admin1234";
		String last_password = "admin8889";
		String new_password = "admin8889";
		NoteResult<Object> result = service.modifyPassword(userId, last_password, new_password);
		System.out.println(result.getStatus()+" "+result.getMsg());
	}
}
