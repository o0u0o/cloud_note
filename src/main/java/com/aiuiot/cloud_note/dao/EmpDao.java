package com.aiuiot.cloud_note.dao;

import com.aiuiot.cloud_note.entity.Emp;
/**
 * 该dao接口用于演示mybatis相关知识
 * @author aiuiot
 *
 */
public interface EmpDao {
	//该抽象方法用于保存新员工
	public void save(Emp emp);
}
