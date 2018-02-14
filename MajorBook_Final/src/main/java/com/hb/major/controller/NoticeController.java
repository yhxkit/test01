package com.hb.major.controller;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hb.major.model.entity.NoticeVo;
import com.hb.major.service.notice.NoticeService;


@Controller
@RequestMapping(value = "notice/")
public class NoticeController {
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private NoticeService noticeService; //공지사항은 코멘트 없음

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String notice(Locale locale, Model model) throws Exception {
		logger.info("공지사항 게시판", locale);
		model.addAttribute("currentmenu", "notice");
		noticeService.noticeListAll(model, 1);
		
		return "user/notice/notice";
	}

	
	@RequestMapping(value = "/page{noticepage}", method = RequestMethod.GET)
	public String board(Locale locale, Model model, @PathVariable("noticepage") int noticepage) {

		logger.info("공지게시판", locale);
		model.addAttribute("currentmenu", "notice");
		
		noticeService.noticeListAll(model, noticepage);
		
		System.out.println("공지게시판 페이지는 " + noticepage);

		return "user/notice/notice";
	}

	@RequestMapping(value = "/{postno}", method = RequestMethod.GET) //상세
	public String detail(Locale locale, Model model, @PathVariable("postno") int no, HttpServletRequest req) {

		logger.info("공지게시글 상세 페이지", locale);

		System.out.println("상세 아이디" + req.getParameter("tempid"));
		model.addAttribute("currentmenu", "notice");

		noticeService.noticeDetailOne(no, model);

		return "user/notice/detail";
	}

	@RequestMapping(value = "/{postno}", method = RequestMethod.PUT) //수정페이지로 입장
	public String editpage(Locale locale, Model model, @PathVariable("postno") int no) {

		logger.info("공지게시글 수정 페이지", locale);
		model.addAttribute("currentmenu", "notice");
		noticeService.noticeDetailOne(no, model);

		return "user/notice/edit";
	}

	@RequestMapping(value = "/completeedit/{postno}", method = RequestMethod.PUT)//수정 실행
	public String edit(@PathVariable("postno") int no, NoticeVo bean, Model model) throws Exception {
		
		noticeService.noticeUpdateOne(bean);
		
		System.out.println("내용 수정");
		
		return "redirect:/notice/";
	}
	
	@RequestMapping(value = "/{postno}", method = RequestMethod.DELETE) //삭제 페이지로
	public String delete(@PathVariable("postno") int no, Model model) throws Exception {
		
		noticeService.noticeDetailOne(no, model);
		return "user/notice/delete";
	}

	@RequestMapping(value = "/delete/{postno}", method = RequestMethod.DELETE) //삭제 실행
	public String deletecompl(@PathVariable("postno") int no, NoticeVo bean, Model model) throws Exception {
	
		
		noticeService.noticeDeleteOne(no);
		System.out.println(no + "번째 공지게시글 삭제");
		return "redirect:/notice/";
	}

	
	
	@RequestMapping(value = "/write", method = RequestMethod.POST) //공지 게시글 입력 페이지
	public String write(Locale locale, Model model) {
		model.addAttribute("currentmenu", "notice");
		logger.info("공지 게시글 작성페이지", locale );
		System.out.println("add 겟 들어옴");
		return "user/notice/write";
	}

	@RequestMapping(value = "/write/writecom", method = RequestMethod.POST) //입력 실행
	public String writeadd(Locale locale, @ModelAttribute NoticeVo bean, HttpServletRequest req, Model model) {
	
		logger.info("공지 게시글 작성", locale);
		System.out.println("add 포스트 들어옴");
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		noticeService.noticeAddOne(bean);
		System.out.println(bean);

		return "redirect:/notice/";
	}
	@RequestMapping(value = "/noticesearch", method = RequestMethod.GET)
	protected String bbsSearch(Locale locale, Model model, HttpServletRequest req, HttpServletResponse res) {

		logger.info("게시판 검색", locale);
		try {
			//req.setCharacterEncoding("UTF-8");
			String keyword = req.getParameter("noticesearchkeyword");
			System.out.println(keyword);
			model.addAttribute("currentmenu", "notice");
			noticeService.noticeSearch(model, keyword);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/notice/notice";
	}
	
	
	
}
