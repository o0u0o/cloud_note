package com.aiuiot.cloud_note.service;

import java.util.List;

import com.aiuiot.cloud_note.entity.Share;
import com.aiuiot.cloud_note.common.utils.NoteResult;

public interface ShareService {
	public NoteResult<Object> shareNote(String noteId);	//根据noteId添加分享
	
	public NoteResult<List<Share>> searchNote(String keyword, int page);	//根据关键字搜索
}
