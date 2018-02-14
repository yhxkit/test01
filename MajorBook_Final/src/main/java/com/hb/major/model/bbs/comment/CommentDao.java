package com.hb.major.model.bbs.comment;

import java.util.List;

import com.hb.major.model.entity.CommentVo;


public interface CommentDao {
	
	List<CommentVo> getCommentList(int postnum) throws Exception;
	void commentInsertOne(CommentVo cmtbean) throws Exception;
	
}
