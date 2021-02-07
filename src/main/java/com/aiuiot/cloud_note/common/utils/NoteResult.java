package com.aiuiot.cloud_note.common.utils;

import com.aiuiot.cloud_note.common.enums.ResponseEnum;
import org.springframework.validation.BindingResult;

import java.io.Serializable;
import java.util.Objects;

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

	/** 泛型，应之后返回的数据类型不同，此处使用泛型的类型 */
	private T data;

	private NoteResult(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	private NoteResult(Integer status, T data, String msg) {
		this.status = status;
		this.data = data;
		this.msg = msg;
	}

	private NoteResult(Integer status, T data) {
		this.status = status;
		this.data = data;
	}


	/**
	 * 成功 返回消息
	 * @param msg
	 * @param <T>
	 * @return
	 */
	public static <T> NoteResult<T> successByMsg(String msg){
		return new NoteResult<>(ResponseEnum.SUCCESS.getCode(), msg);
	}

	public static <T> NoteResult<T> success(T data, String msg){
		return new NoteResult<T>(ResponseEnum.SUCCESS.getCode(), data, msg);
	}

	/**
	 * 成功 返回一个对象
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> NoteResult<T> success(T data){
		return new NoteResult<T>(ResponseEnum.SUCCESS.getCode(), data);
	}

	/**
	 * 成功
	 * @param <T>
	 * @return
	 */
	public static <T> NoteResult<T> success(){
		return new NoteResult<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc());
	}

	public static <T> NoteResult<T> error(ResponseEnum responseEnum){
		return new NoteResult<>(responseEnum.getCode(), responseEnum.getDesc());
	}

	public static <T> NoteResult<T> error(ResponseEnum responseEnum, String msg){
		return new NoteResult<>(responseEnum.getCode(), msg);
	}

	public static <T> NoteResult error(ResponseEnum responseEnum, BindingResult bindingResult){
		return new NoteResult<>(responseEnum.getCode(), Objects.requireNonNull(bindingResult.getFieldError().getField() + " " + bindingResult.getFieldError()));
	}
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
