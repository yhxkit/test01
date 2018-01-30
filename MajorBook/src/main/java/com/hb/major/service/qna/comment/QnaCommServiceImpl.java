package com.hb.major.service.qna.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hb.major.model.entity.QnaCommentVo;
import com.hb.major.model.qna.comment.QnaCommDao;

@Service
public class QnaCommServiceImpl implements QnaCommService {
	@Autowired
	QnaCommDao qnaCommDao;
	
	@Override
	public void qnaCommentList(int postnum, Model cmtmodel) {
		try {
			cmtmodel.addAttribute("cmtlist", qnaCommDao.qnaGetCommentList(postnum));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void qnaCommentAddOne(QnaCommentVo cmtbean) {
		try {
			qnaCommDao.qnaCommentInsertOne(cmtbean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
