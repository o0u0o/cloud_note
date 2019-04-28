package com.aiuiot.cloud_note.dao;

import java.util.List;
import java.util.Map;

import com.aiuiot.cloud_note.entity.Share;

public interface ShareDao {
	public List<Share> findLikeTitle(Map params);	//根据笔记标题查询
	
	public Share findByNoteId(String noteId);	//根据noteId
	
	public void save(Share share);	//保存分享
}
