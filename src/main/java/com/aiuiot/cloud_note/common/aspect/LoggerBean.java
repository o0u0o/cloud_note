package com.aiuiot.cloud_note.common.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 新增切面
 * @author aiuiot
 *
 */
@Component
@Aspect
public class LoggerBean {
	@Before("within(com.aiuiot.cloud_note.controller..*)")	//通知:写切入点
	public void logController() {
		System.out.println("AOP功能注入!");
	}

	@Before("within(com.aiuiot.cloud_note.service..*)")
	public void logService() {
		System.out.println("切入service成功");
	}
}
