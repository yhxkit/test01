package com.hb.major.controller;

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

import com.hb.major.model.entity.QnaVo;
import com.hb.major.service.qna.QnaService;
import com.hb.major.service.qna.comment.QnaCommService;


@Controller
//@RequestMapping(value = "question/")
public class QnaController {
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
@Autowired
private QnaService qnaService; 
@Autowired
private QnaCommService qnaCommService;

///question : 관리자 페이지      /myqna : 유저 페이지 
//권한 처리를 세션으로 하려고 했으나 카카오에서 로그인 유효시간 세션을 받아오지 못해 주소로 접근하는 방법에 대한 대처를 제외하고 처리

	@RequestMapping(value = "/question/", method = RequestMethod.GET)
	public String board(Locale locale, Model model, HttpServletRequest req) {
	
		logger.info("게시판", locale);
		model.addAttribute("currentmenu", "question");
		
//		String userid = req.getParameter("userid");
//		String master = req.getParameter("master");
//		
//		System.out.println("질답게 들어온값" + userid+master);
//		
//		
//		if(master=="true") {
//			
//			qnaService.qnaListAll(model, 1);
//			
//			return "user/qna/question";
//		
//		}else {
//			
//			qnaService.qnaMyListAll(model, userid);
//			
//			return "user/qna/write";
//		}
		
		
		qnaService.qnaListAll(model, 1);
		return "user/qna/question";
		
	}

	
	@RequestMapping(value = "/question/page{qnapage}", method = RequestMethod.GET)
	public String board(Locale locale, Model model, @PathVariable("qnapage") int qnapage, HttpServletRequest req) {

		logger.info("질문 게시판", locale);
		System.out.println("관리자용 질문 게시판 페이지는 " + qnapage);

		model.addAttribute("currentmenu", "question");

//		String userid = req.getParameter("userid");
//		String master = req.getParameter("master");
//		
//		System.out.println("질답게 들어온값" + userid+master);
//		
//		if(master=="true") {
//			
//			qnaService.qnaListAll(model, qnapage);
//			return "user/qna/question";
//			
//		}else {
//			
//			qnaService.qnaMyListAll(model, userid);
//			return "user/qna/write";
//			
//		}
//		
	
		
		
		qnaService.qnaListAll(model, qnapage);
		return "user/qna/question";
		
	}

	
	
	
	
	@RequestMapping(value = "/question/{postno}", method = RequestMethod.GET) //상세
	public String detail(Locale locale, Model model, @PathVariable("postno") int no, HttpServletRequest req) {

		logger.info("문의사항 게시글 상세 페이지", locale);

		System.out.println("상세 아이디" + req.getParameter("tempid"));
		model.addAttribute("currentmenu", "question");

		qnaService.qnaDetailOne(no, model);
		qnaCommService.qnaCommentList(no, model);

		return "user/qna/detail";
	}

	
	
	@RequestMapping(value = "/myqna", method = RequestMethod.GET)
	public String myBoard(Locale locale, Model model, HttpServletRequest req) {
	
		logger.info("게시판", locale);
		model.addAttribute("currentmenu", "question");
		
		String userid = req.getParameter("userid");
		qnaService.qnaMyListAll(model, userid);
		
		System.out.println("qna 유저 "+userid);
		return "user/qna/write";
		
	}

	
	@RequestMapping(value = "/myqna/{postno}", method = RequestMethod.GET) //상세
	public String myDetail(Locale locale, Model model, @PathVariable("postno") int no, HttpServletRequest req) {

		logger.info("문의사항 게시글 상세 페이지", locale);

		System.out.println("상세 아이디" + req.getParameter("tempid"));
		model.addAttribute("currentmenu", "question");

		qnaService.qnaDetailOne(no, model);
		qnaCommService.qnaCommentList(no, model);
		String userid = req.getParameter("userid");
		qnaService.qnaMyListAll(model, userid);

		return "user/qna/detail";
	}
	
	
	
	@RequestMapping(value = "/myqna/write/writecom", method = RequestMethod.POST) //쓰기는 유저만
	public String writeadd(Locale locale, @ModelAttribute QnaVo bean, HttpServletRequest req, Model model) {
		logger.info("문의사항 게시글 작성", locale);
		System.out.println("add 포스트 들어옴");
		
		String usernick="";
		String userid="";
		String master="false";//글쓰는 사람 유저밖에 없으니 권한 false
		try {
			req.setCharacterEncoding("UTF-8");
			
			usernick = req.getParameter("qnaNick");
			userid = req.getParameter("qnaId");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("qna addone 컨트롤러에서 받은 값 "+usernick+":"+userid+";"+master+"="+bean);
		System.out.println("컨값");
		qnaService.qnaAddOne(bean);

		return "redirect:/myqna/?userid="+userid+"&master="+master;
	}
	
	
	@RequestMapping(value = "/myqna/{postno}", method = RequestMethod.PUT) //수정페이지로 입장
	public String editpage(Locale locale, Model model, @PathVariable("postno") int no) {

		logger.info("문의사항 수정 페이지", locale);
		model.addAttribute("currentmenu", "question");
		qnaService.qnaDetailOne(no, model);
		
		return "user/qna/edit";
	}
	
	@RequestMapping(value = "/myqna/completeedit/{postno}", method = RequestMethod.PUT)//수정 실행
	public String edit(@PathVariable("postno") int no, QnaVo bean, Model model) throws Exception {
		qnaService.qnaUpdateOne(bean);
		System.out.println(bean.getQnaNo()+"번째 글 : " + bean.getQnaTitle() + "," + bean.getQnaContent());
		System.out.println("문의사항 내용 수정");
		
		return "redirect:/myqna/"+no;
	
	}
	
	@RequestMapping(value = "/myqna/{postno}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("postno") int no, Model model) throws Exception {
		System.out.println("delete처음페이지로");
		qnaService.qnaDetailOne(no, model);
		return "user/qna/delete";
	}

	@RequestMapping(value = "/myqna/delete/{postno}", method = RequestMethod.DELETE)
	public String deletecompl(@PathVariable("postno") int no, QnaVo bean, Model model, HttpServletRequest req) throws Exception {
	
		String id = req.getParameter("qnaId");
		System.out.println(id);
		qnaService.qnaDeleteOne(no);
		System.out.println(no + "번째 게시글 삭제");
		return "redirect:/myqna/?userid="+id+"&master=false";//어차피 자기글 삭제되는거 유저니까 권한 false 줌
	}
	

	@RequestMapping(value = "/question/qnasearch", method = RequestMethod.GET)
	protected String bbsSearch(Locale locale, Model model, HttpServletRequest req, HttpServletResponse res) {

		logger.info("게시판 검색", locale);
		try {
			//req.setCharacterEncoding("UTF-8");
			String keyword = req.getParameter("qnasearchkeyword");
			System.out.println(keyword);
			model.addAttribute("currentmenu", "question");
			qnaService.qnaSearch(model, keyword);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/qna/question";
	}
	
	

	
	@RequestMapping(value = "/myqna/qnasearch", method = RequestMethod.GET)
	protected String myBbsSearch(Locale locale, Model model, HttpServletRequest req, HttpServletResponse res) {

		logger.info("게시판 검색", locale);
		try {
			//req.setCharacterEncoding("UTF-8");
			String keyword = req.getParameter("qnasearchkeyword");
			System.out.println(keyword);
			model.addAttribute("currentmenu", "question");
			qnaService.qnaSearch(model, keyword);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/qna/question";
	}
	
	
}
