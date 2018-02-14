package com.hb.major.model.checkMaster;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CheckMasterDaoImple implements CheckMasterDao {
	
	SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	
	
	@Override
	public int masterCountId(String id) throws Exception {

		return session.selectOne("com.hb.major.model.checkMaster.CheckMasterDao.masterCountId", id);
		
	}

}
