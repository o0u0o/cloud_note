package com.aiuiot.cloud_note.common.aspect;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component //扫描到Spring容器
@Aspect	//将该类作为切面组件
public class ExceptionBean {
	//e是目标组件抛出来的异常对象
	@AfterThrowing(throwing="e",pointcut="within(com.aiuiot.cloud_note.service..*)")
	public void execute(Exception e) {
		//将异常信息输入文件
		try {
			FileWriter fw = new FileWriter("/Volumes/SD/学习/Java/视频/note_error.log",true);
			PrintWriter pw = new PrintWriter(fw);
			//利用pw对象写入异常信息

			//(1)时间信息
			Date time = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String timeStr = sdf.format(time);
			pw.println("*******************************");
			pw.println("*异常类型:" + e);
			pw.println("*异常时间:" + timeStr);
			pw.println("**********异常详细信息**********");
			e.printStackTrace(pw);
			pw.close();
			fw.close();
		} catch (IOException exception) {
			System.out.println("记录异常失败!");
		}

	}
}
