package com.hb.major.model.user;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hb.major.model.entity.UserVo;

@Repository
public class UserDaoImpl implements UserDao {
	SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public void userInsertOne(UserVo bean) throws Exception {
		session.insert("com.hb.major.model.user.UserDao.userInsertOne", bean);
	}

	@Override
	public int userDeleteOne(String no) throws Exception {
		return session.delete("com.hb.major.model.user.UserDao.userDeleteOne", no);
	}

	@Override
	public UserVo userSelectOne(String no) throws Exception {
		return session.selectOne("com.hb.major.model.user.UserDao.userSelectOne", no);
	}

	@Override
	public int userUpdateOne(UserVo bean) throws Exception {
		return session.update("com.hb.major.model.user.UserDao.userUpdateOne", bean);
	}

	

}
