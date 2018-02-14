package com.hb.major.service.bbs;

import org.springframework.ui.Model;

import com.hb.major.model.entity.BbsVo;

public interface BbsService {
	void bbsListAll(Model model, int currentpage);
	void bbsAddOne(BbsVo bean);
	void bbsDetailOne(int no, Model model);
	void bbsUpdateOne(BbsVo bean);
	void bbsDeleteOne(int no);
	void bbsSearch(Model model, String keyword);
	void bbsStatus(Model model, String status);
}
