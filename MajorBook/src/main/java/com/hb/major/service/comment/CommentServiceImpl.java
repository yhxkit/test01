package com.hb.major.service.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hb.major.model.bbs.BbsDao;
import com.hb.major.model.bbs.comment.CommentDao;
import com.hb.major.model.entity.CommentVo;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDao commentDao;
	
	
	@Override
	public void commentList(int postnum, Model cmtmodel) {
			try {
				cmtmodel.addAttribute("cmtlist", commentDao.getCommentList(postnum));
			} catch (Exception e) {
				e.printStackTrace();
			} 
	}
	
	@Override
	public void commentAddOne(CommentVo cmtbean){
		try {
			commentDao.commentInsertOne(cmtbean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
