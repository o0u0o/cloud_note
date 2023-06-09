package com.aiuiot.cloud_note.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public class Book implements Serializable{

	/** 笔记本ID */
	private Long cn_notebook_id;

	/** 用户ID */
	private String cn_user_id;

	/** 笔记本类型ID */
	private String cn_notebook_type_id;

	/** 笔记本名 */
	private String cn_notebook_name;

	private String cn_notebook_desc;	//笔记本说明

	private Timestamp cn_notebook_createtime;	//笔记本创建时间

	private User user;	//用户对象
	
	
}
