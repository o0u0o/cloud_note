package test.dao;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aiuiot.cloud_note.dao.UserDao;
import com.aiuiot.cloud_note.entity.User;
import com.aiuiot.cloud_note.util.NoteUtil;

import test.TestBase;



public class TestUserDao extends TestBase {
	
	private UserDao dao;
	
//	@Before
//	public void init() {
//		dao = super.getContext().getBean("userDao",UserDao.class);
//	}
//	
	
	@Before
	public void init() {
		//实例化上下文对象
		String[] conf = {"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);  
		dao = ac.getBean("userDao",UserDao.class);
	}
	
	//测试Dao接口
	@Test
	public void testLogin() {
		User user = dao.findByName("admin");
		if(user != null) {
			System.out.println(user);
		}else {
			System.out.println("用户不存在！");
		}
	}
	                                                                                   
	@Test
	public void testSave() {
		//实例化user对象，模拟测试数据
		User user = new User();
		user.setCn_user_id("10086");
		user.setCn_user_name("ppx");
		user.setCn_user_nick("皮皮虾");
		user.setCn_user_password("ppx1234");
		//user.setCn_user_token("1008611");
		dao.save(user);
		System.out.println(user);
	}
	
	@Test //测试用例-3 测试根据用户ID查询
	public void testChangePwd() {
		String new_password = "admin1234";	//新密码
		String input_password = "123456";	
		
		String md5New_password = NoteUtil.md5(new_password);
		String md5Input_password = NoteUtil.md5(input_password);
		String userId = "0c9f285aa2ea47238aea1312e0f2e747";
		
		System.out.println("加密后的输入密码:"+md5Input_password);
		System.out.println("加密后的新密码:"+md5New_password);
		
		User user = dao.findByUserId(userId);
		String last_password = user.getCn_user_password();
		System.out.println("原始密码:"+last_password);
		
		if(last_password.equals(md5Input_password)) {
			//修改密码
			user.setCn_user_password(md5New_password);
			dao.updatePassword(user);
			//System.out.println("影响行数:"+row);
			System.out.println("更新后的用户信息");
			System.out.println(user.getCn_user_name());
			System.out.println(user.getCn_user_password());
			
		}else {
			System.out.println("修改密码失败");
		}
	}
	
}
