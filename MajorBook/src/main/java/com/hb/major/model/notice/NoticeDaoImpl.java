package com.hb.major.model.notice;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hb.major.model.entity.NoticeVo;

@Repository
public class NoticeDaoImpl implements NoticeDao {
	SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<NoticeVo> noticeSelectAll() throws Exception {
		return session.selectList("com.hb.major.model.notice.NoticeDao.noticeSelectAll");
	}

	@Override
	public void noticeInsertOne(NoticeVo bean) throws Exception {
		
		session.insert("com.hb.major.model.notice.NoticeDao.noticeInsertOne", bean);
	}

	@Override
	public NoticeVo noticeSelectOne(int no) throws Exception {
		return session.selectOne("com.hb.major.model.notice.NoticeDao.noticeSelectOne", no);
	}

	@Override
	public int noticeUpdateOne(NoticeVo bean) throws Exception {
		
		System.out.println(bean);
		return session.update("com.hb.major.model.notice.NoticeDao.noticeUpdateOne", bean);
	}

	@Override
	public int noticeDeleteOne(int no) throws Exception {
		return session.delete("com.hb.major.model.notice.NoticeDao.noticeDeleteOne", no);
	}

	@Override
	public List<NoticeVo> noticeSelectPage(int currentpage) throws Exception {
		return session.selectList("com.hb.major.model.notice.NoticeDao.noticeSelectPage", (currentpage-1)*10);
	}

	@Override
	public List<NoticeVo> noticeSelectWhere(String keyword) throws Exception {
		return session.selectList("com.hb.major.model.notice.NoticeDao.noticeSelectWhere", keyword);
	}

}
