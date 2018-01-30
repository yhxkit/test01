package com.hb.major.model.user;

import java.util.List;

import com.hb.major.model.entity.UserVo;

public interface UserDao {
	UserVo userSelectOne(String no) throws Exception;
	void userInsertOne(UserVo bean) throws Exception;
	int userUpdateOne(UserVo bean) throws Exception;
	int userDeleteOne(String no) throws Exception;
}
