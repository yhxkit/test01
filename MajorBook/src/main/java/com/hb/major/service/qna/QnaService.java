package com.hb.major.service.qna;

import org.springframework.ui.Model;

import com.hb.major.model.entity.QnaVo;

public interface QnaService {
	void qnaListAll(Model model, int currentpage);
	void qnaAddOne(QnaVo bean);
	void qnaDetailOne(int no, Model model);
	void qnaUpdateOne(QnaVo bean);
	void qnaDeleteOne(int no);
	void qnaSearch(Model model, String keyword);
	void qnaMyListAll(Model model, String id);
}
