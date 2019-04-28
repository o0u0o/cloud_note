package test.dao;

import org.junit.Before;
import org.junit.Test;

import com.aiuiot.cloud_note.dao.EmpDao;
import com.aiuiot.cloud_note.entity.Emp;

import test.TestBase;

public class TestEmpDao extends TestBase{
	private EmpDao empDao;
	
	@Before
	public void init() {
		empDao = super.getContext().getBean("empDao",EmpDao.class);
	}
	
	@Test //测试用例-1 测试新增emp表中的员工，并获取ID
	public void test() {
		Emp emp = new Emp();
		emp.setName("李四");
		emp.setAge(14);
		empDao.save(emp);
		int id = emp.getId();
		
		System.out.println("刚插入的ID:"+id);
		System.out.println(emp);
	}
}
