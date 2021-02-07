//package com.aiuiot.cloud_note.aspect;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
///**
// * 该切面程序用于代码审计(audit)
// * @author aiuiot
// *
// */
//@Component
//@Aspect
//public class AuditBean {
//
//	@Around("within(com.aiuiot.cloud_note.service..*)")
//	public Object audit(ProceedingJoinPoint point) throws Throwable {
//		Object obj = null;
//
//		try {
//			long timeStart = System.currentTimeMillis();
//			obj = point.proceed();
//			long timeEnd = System.currentTimeMillis();
//			long time = timeEnd - timeStart;
//			String str = point.getSignature().toString();
//			System.out.println(str+"耗时:"+time+"毫秒");
//		} catch (Throwable e) {
//			e.printStackTrace();
//			throw e;
//		}
//
//		return obj;
//	}
//}
