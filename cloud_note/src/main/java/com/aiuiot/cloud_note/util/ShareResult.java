package com.aiuiot.cloud_note.util;

import java.io.Serializable;
/**
 * 废弃代码
 * @author aiuiot
 *
 * @param <T>
 */
public class ShareResult<T> implements Serializable {
	private int status;	//状态
	private String msg;	//信息
	private T data;	//泛型
	
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
		return "ShareResult [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
}
