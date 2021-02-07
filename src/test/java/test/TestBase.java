package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 该抽象类用于方便测试
 * @author aiuiot
 *
 */
public abstract class TestBase {
	public ApplicationContext getContext() {
		
		//定义数组，放入配置文件
		String[] conf = {
				"conf/spring-mvc.xml",
				"conf/spring-mybatis.xml",
			};

		//实例化ctx对象
		ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
		
		return ctx;
	}
}
