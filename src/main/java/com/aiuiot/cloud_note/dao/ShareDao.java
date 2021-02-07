package com.aiuiot.cloud_note.dao;

import java.util.List;
import java.util.Map;

import com.aiuiot.cloud_note.entity.Share;

public interface ShareDao {

	/**
	 * 根据笔记标题查询
	 * @param params
	 * @return
	 */
	public List<Share> findLikeTitle(Map params);

	/**
	 * 根据noteId
	 * @param noteId
	 * @return
	 */
	public Share findByNoteId(String noteId);
	
	public void save(Share share);	//保存分享
}
