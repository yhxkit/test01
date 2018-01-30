package com.hb.major.service.qna.comment;

import org.springframework.ui.Model;

import com.hb.major.model.entity.QnaCommentVo;

public interface QnaCommService {
	void qnaCommentList(int postnum, Model model);
	void qnaCommentAddOne(QnaCommentVo cmtbean);
}
