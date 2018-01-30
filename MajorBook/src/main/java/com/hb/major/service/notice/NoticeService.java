package com.hb.major.service.notice;

import org.springframework.ui.Model;

import com.hb.major.model.entity.NoticeVo;

public interface NoticeService {
	void noticeListAll(Model model, int currentpage);
	void noticeAddOne(NoticeVo bean);
	void noticeDetailOne(int no, Model model);
	void noticeUpdateOne(NoticeVo bean);
	void noticeDeleteOne(int no);
	void noticeSearch(Model model, String keyword);
}
