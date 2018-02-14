package com.hb.major.model.qna.comment;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hb.major.model.entity.QnaCommentVo;
@Repository
public class QnaCommDaoImpl implements QnaCommDao {
	
	SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<QnaCommentVo> qnaGetCommentList(int postnum) throws Exception {
		return session.selectList("com.hb.major.model.qna.comment.QnaCommDao.qnaGetCommentList", postnum);
	}

	@Override
	public void qnaCommentInsertOne(QnaCommentVo cmtbean) throws Exception {
		session.insert("com.hb.major.model.qna.comment.QnaCommDao.qnaCommentInsertOne", cmtbean);
	}

}
