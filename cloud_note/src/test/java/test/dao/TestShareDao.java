package test.dao;

import org.junit.Before;
import org.junit.Test;

import com.aiuiot.cloud_note.dao.ShareDao;
import com.aiuiot.cloud_note.entity.Share;

import test.TestBase;

public class TestShareDao extends TestBase{
	private ShareDao dao;
	
	@Before
	public void init() {
		dao = super.getContext().getBean("shareDao",ShareDao.class);
	}
	
	@Test	//测试用例-1 根据noteId查询ShareId
	public void test1() {
		String noteId = "e5f14cc96e984d518db6781c02efbef8";
		//String noteId = "e5f14cc96e984d518db6781c02efbef0";
		Share share = dao.findByNoteId(noteId);
		if(share==null) {
			System.out.println("数据库没有该noteId的信息");
		}else {
			String shareId = share.getCn_share_id();
			String title = share.getCn_share_title();
			String body = share.getCn_share_body();
			
			System.out.println("shareId:"+shareId);	
			System.out.println("title:"+title);
			System.out.println("body:"+body);
		}
	}
	
	@Test
	public void test2() {
		Share share = new Share();
		
		share.setCn_share_id("00000-00000-00000-00002");
		share.setCn_share_title("28天骑行西藏");
		share.setCn_share_body("笔记笔记。。。");
		share.setCn_note_id("003ec2a1-f975-4322-8e4d-dfd206d6ac0c");
		dao.save(share);
		System.out.println(share);
	}
}
