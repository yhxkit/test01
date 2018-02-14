package com.hb.major.model.bbs;

import java.util.List;

import com.hb.major.model.entity.BbsVo;

public interface BbsDao {
	List<BbsVo> bbsSelectAll() throws Exception;
	void bbsInsertOne(BbsVo bean) throws Exception;
	BbsVo bbsSelectOne(int no) throws Exception;
	int bbsUpdateOne(BbsVo bean) throws Exception;
	int bbsDeleteOne(int no) throws Exception;
	List<BbsVo> bbsSelectPage(int currentpage) throws Exception;
	List<BbsVo> bbsSelectWhere(String keyword) throws Exception;
	List<BbsVo> bbsSelectStatus(String status) throws Exception;
}
