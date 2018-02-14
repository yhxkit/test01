package com.hb.major.model.qna.comment;

import java.util.List;

import com.hb.major.model.entity.QnaCommentVo;

public interface QnaCommDao {
	List<QnaCommentVo> qnaGetCommentList(int postnum) throws Exception;
	void qnaCommentInsertOne(QnaCommentVo cmtbean) throws Exception;
}
