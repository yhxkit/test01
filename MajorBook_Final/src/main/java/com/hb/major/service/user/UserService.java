package com.hb.major.service.user;

import com.hb.major.model.entity.UserVo;

public interface UserService {
	
	void userInsertOne(UserVo bean);
	void userDeleteOne(String no);
	void userSelectOne(String no);
	void userUpdateOne(UserVo bean);
	
	void userCheck(UserVo bean);
	
}
