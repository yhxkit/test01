package com.hb.major.model.qna;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hb.major.model.entity.QnaVo;

@Repository
public class QnaDaoImpl implements QnaDao {
	SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<QnaVo> qnaSelectAll() throws Exception {
		return session.selectList("com.hb.major.model.qna.QnaDao.qnaSelectAll");
	}

	@Override
	public void qnaInsertOne(QnaVo bean) throws Exception {
		session.insert("com.hb.major.model.qna.QnaDao.qnaInsertOne", bean);
	}

	@Override
	public QnaVo qnaSelectOne(int no) throws Exception {
		return session.selectOne("com.hb.major.model.qna.QnaDao.qnaSelectOne", no);
	}

	@Override
	public int qnaUpdateOne(QnaVo bean) throws Exception {
		return session.update("com.hb.major.model.qna.QnaDao.qnaUpdateOne", bean);
	}

	@Override
	public int qnaDeleteOne(int no) throws Exception {
		return session.delete("com.hb.major.model.qna.QnaDao.qnaDeleteOne", no);
	}

	@Override
	public List<QnaVo> qnaSelectPage(int currentpage) throws Exception {
		return session.selectList("com.hb.major.model.qna.QnaDao.qnaSelectPage", (currentpage-1)*10);
	}

	@Override
	public List<QnaVo> qnaSelectWhere(String keyword) throws Exception {
		return session.selectList("com.hb.major.model.qna.QnaDao.qnaSelectWhere", keyword);
	}

	@Override
	public List<QnaVo> qnaSelectWhereMylist(String userid) throws Exception {
		return session.selectList("com.hb.major.model.qna.QnaDao.qnaSelectWhereMylist",userid);
	}

}
