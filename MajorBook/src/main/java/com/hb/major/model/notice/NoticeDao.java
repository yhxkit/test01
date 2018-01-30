package com.hb.major.model.notice;

import java.util.List;

import com.hb.major.model.entity.NoticeVo;

public interface NoticeDao {
	List<NoticeVo> noticeSelectAll() throws Exception;
	void noticeInsertOne(NoticeVo bean) throws Exception;
	NoticeVo noticeSelectOne(int no) throws Exception;
	int noticeUpdateOne(NoticeVo bean) throws Exception;
	int noticeDeleteOne(int no) throws Exception;
	List<NoticeVo> noticeSelectPage(int currentpage) throws Exception;
	List<NoticeVo> noticeSelectWhere(String keyword) throws Exception;
}
