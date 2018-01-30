package com.hb.major.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hb.major.model.entity.UserVo;
import com.hb.major.service.user.UserService;

@Controller
public class AccountController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/userdelete", method = RequestMethod.GET)
	public String unlinkPage() {
		System.out.println("앱 연결 해제 페이지 이동 (회원 탈퇴)");
		return "loginoutdel/userdelete";
	}
	
	@RequestMapping(value = "/userdelete", method = RequestMethod.DELETE)
	public String unlink(@ModelAttribute UserVo bean, HttpServletRequest req) {
		// db에서 아이디 삭제해야하니까 post방식으로
		String delid = req.getParameter("delid");
		System.out.println("탈퇴 들어옴");
		System.out.println(delid);
		userService.userDeleteOne(delid);
		return "redirect:/";
	}

}
