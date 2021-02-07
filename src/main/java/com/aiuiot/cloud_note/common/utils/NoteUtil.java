package com.aiuiot.cloud_note.common.utils;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
public class NoteUtil {
	
	/**
	 * 利用UUID算法生成主键
	 * @return
	 */
	public static String createId(){
		UUID uuid = UUID.randomUUID();	//获得一个uuid
		String id = uuid.toString();	//通过toString转换为字符串
		
		return id.replace("-",""); 		//去掉生成的"-"
	}
	
	
	/**
	 * MD5加密处理
	 * @param src
	 * @return
	 */
	public static String md5(String src) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			//MD5加密处理
			byte[] output = md.digest(src.getBytes());
			//Base64处理（将自己转成字符串输出 构成a-z、A-Z、0-9、+、=）
			String ret = Base64.encodeBase64String(output);
			return ret;
		} catch (Exception e) {
			//此次异常处理待改进	throw new NoteException("密码加密失败",e);
			throw new RuntimeException("加密失败",e);
		}
	}
	
	/**
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args) {		
		//测试MD5加密
		System.out.println(md5("123456"));
		System.out.println(md5("123456"));
		
		//测试uuid生成
		System.out.println(createId());
		
		System.out.println(md5(createId()));
		
	}
}
