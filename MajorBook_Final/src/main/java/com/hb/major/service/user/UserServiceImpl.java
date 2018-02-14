package com.hb.major.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hb.major.model.entity.UserVo;
import com.hb.major.model.user.UserDao;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	
	@Override
	public void userInsertOne(UserVo bean) {
		try {
			userDao.userInsertOne(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void userDeleteOne(String no) {
		try {
			userDao.userDeleteOne(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void userSelectOne(String no) {
		try {
			userDao.userSelectOne(no);
			System.out.println(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void userUpdateOne(UserVo bean) {
		try {
			userDao.userUpdateOne(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void userCheck(UserVo bean) {
		//유저 중복 체크하기
		
		System.out.println("user체크 들어줌");
		
		String checkId = bean.getKakao_id();

		
		
		try {
			UserVo tempbean = userDao.userSelectOne(checkId);
			
			System.out.println(tempbean);
			//널값이면 insertId 해서 db에 아이디 저장
			
			
			if(tempbean==null) {
				userDao.userInsertOne(bean);
				
				System.out.println("id가 널이다. 즉 db에 값이 없다");
				
			}else {
				
				boolean nickchng = true;
				boolean imgchng = true;
				boolean thumchng = true;
				//기존의 아이디의 정보가 업뎃이 있는지 없는지 체크
				if(bean.getKakao_nick().equals(tempbean.getKakao_nick())) {
					nickchng = false;
					System.out.println("닉이 기존과 같다...");
				}
				if(bean.getKakao_profile_image().equals(tempbean.getKakao_profile_image())) {
					imgchng = false;
					System.out.println("플필사진이 기존과 같다");
				}
				if(bean.getKakao_thumbnail_image().equals(tempbean.getKakao_thumbnail_image())){
					thumchng = false;
					System.out.println("썸이 기존과 같다");
				}
				if(nickchng == true || imgchng == true || thumchng == true) {
					System.out.println("닉/이미지/썸 중 하나가 바뀌어서 업뎃 들어감");
				userDao.userUpdateOne(tempbean);
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}


}
