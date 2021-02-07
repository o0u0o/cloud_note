package com.aiuiot.cloud_note.util;

import java.io.Serializable;
/**
 * 
 * @Title:  NoteResult.java   
 * @Package com.aiuiot.cloud_note.util   
 * @Description:   结果类，存放结果  
 * @author: ZerOneth 
 * @date:   2019年4月23日 上午11:59:23   
 * @version V1.0
 */
public class NoteResult<T> implements Serializable {

	/** 状态 */
	private int status;

	/** 消息 */
	private String msg;

	/** //泛型，应之后返回的数据类型不同，此处使用泛型的类型 */
	private T data;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "NoteResult [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
	
}
