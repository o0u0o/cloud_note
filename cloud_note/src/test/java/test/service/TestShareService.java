package test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.aiuiot.cloud_note.entity.Share;
import com.aiuiot.cloud_note.service.ShareService;
import com.aiuiot.cloud_note.util.NoteResult;
import com.aiuiot.cloud_note.util.ShareResult;

import test.TestBase;

public class TestShareService extends TestBase{
	@Resource
	private ShareService service;
	
	@Before
	public void init() {
		service = super.getContext().getBean("shareService",ShareService.class);
	}
	
	@Test
	public void test1() {
		//String noteId = "d9d732d74bd6401580a5bdc15faf40e6";
		String noteId = "a554f158-ac7a-44cc-8b9a-6de8dddd8fa3";
		NoteResult<Object> result1 = service.shareNote(noteId);
			System.out.println("该ID已存在");
			result1.getData();
			NoteResult<Object> result2 = service.shareNote(noteId);
			System.out.println(result2);
	}
	
	@Test	//测试用例-2: 测试查询分享笔记
	public void test2() {
		String keyword = "测试";
		int page = 3;
		NoteResult<List<Share>> result = service.searchNote(keyword, page);
		System.out.println("状态值:"+result.getStatus());
		System.out.println("信  息:"+result.getMsg());
		List<Share> list = result.getData();
		for (Share share : list) {
			System.out.println("分享ID:"+share.getCn_share_id());
			System.out.println("笔记ID:"+share.getCn_note_id());
			System.out.println("笔记标题:"+share.getCn_share_title());
		}
		System.out.println(result.getData());
	}
}
