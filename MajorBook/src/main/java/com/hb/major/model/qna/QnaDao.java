package com.hb.major.model.qna;

import java.util.List;

import com.hb.major.model.entity.QnaVo;

public interface QnaDao {
	List<QnaVo> qnaSelectAll() throws Exception;
	void qnaInsertOne(QnaVo bean) throws Exception;
	QnaVo qnaSelectOne(int no) throws Exception;
	int qnaUpdateOne(QnaVo bean) throws Exception;
	int qnaDeleteOne(int no) throws Exception;
	List<QnaVo> qnaSelectPage(int currentpage) throws Exception;
	List<QnaVo> qnaSelectWhere(String keyword) throws Exception;
	
	List<QnaVo> qnaSelectWhereMylist(String userid) throws Exception;
}
