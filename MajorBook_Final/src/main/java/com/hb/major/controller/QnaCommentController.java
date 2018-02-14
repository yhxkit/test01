package com.hb.major.controller;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hb.major.model.entity.QnaCommentVo;
import com.hb.major.service.qna.comment.QnaCommService;

@Controller
public class QnaCommentController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private QnaCommService qnaCommService;
	
	@RequestMapping(value = "/question/writecomment", method = RequestMethod.POST)
	public String writecmt(Locale locale, @ModelAttribute QnaCommentVo cmtbean, HttpServletRequest req) {
		
		logger.info("코멘트 작성", locale);
		System.out.println("코멘트 작성 post 들어옴");
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		int boardNum = Integer.parseInt(req.getParameter("qnaListNo"));
		System.out.println(boardNum);
		cmtbean.setQnaListNo(boardNum);
		qnaCommService.qnaCommentAddOne(cmtbean);
		System.out.println(cmtbean);

		return "redirect:/question/"+cmtbean.getQnaListNo();
	}
	
	
	
	@RequestMapping(value = "/myqna/writecomment", method = RequestMethod.POST)
	public String mywritecmt(Locale locale, @ModelAttribute QnaCommentVo cmtbean, HttpServletRequest req) {
		
		logger.info("코멘트 작성", locale);
		System.out.println("코멘트 작성 post 들어옴");
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		int boardNum = Integer.parseInt(req.getParameter("qnaListNo"));
		System.out.println(boardNum);
		cmtbean.setQnaListNo(boardNum);
		qnaCommService.qnaCommentAddOne(cmtbean);
		System.out.println(cmtbean);

		return "redirect:/myqna/"+cmtbean.getQnaListNo();
	}	
	
}
