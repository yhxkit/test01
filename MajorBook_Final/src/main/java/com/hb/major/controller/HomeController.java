package com.hb.major.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hb.major.model.entity.UserVo;
import com.hb.major.service.user.UserService;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserService userService;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model menumodel, HttpServletRequest req) {
		logger.info("The client locale is {}.", locale);
		menumodel.addAttribute("currentmenu", "home");
		
		return "main";
	}

/*	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String login(@ModelAttribute UserVo bean, HttpServletRequest req, Model menumodel) throws Exception {

		req.setCharacterEncoding("UTF-8");
		menumodel.addAttribute("constate","connected");
		menumodel.addAttribute("currentmenu", "home");
		
	//	userService.userCheck(bean);
		
		return "main";
	}*/

	@RequestMapping(value = "/aboutus", method = RequestMethod.GET)
	public String aboutus(Locale locale, Model model) throws Exception {
		logger.info("ABOUT US", locale);
		
		model.addAttribute("currentmenu", "aboutus");
		return "user/aboutus";
	}

}