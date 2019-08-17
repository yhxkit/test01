package com.beat.Lecture.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beat.Lecture.model.MyLectureDao;
import com.beat.Lecture.model.MyLectureDto;

@WebServlet("/myLecture.lms")
public class MyLecturePageController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		HttpSession session = req.getSession();
		
		String mid = (String)session.getAttribute("mid");
		System.out.println("현재 세션의 아이디는?!"+mid);
		
		String url;
		
		
		
		if(mid==null || mid.equals("guest")){
			url="/login/login.jsp";
		}else{
			url="/lecture/mylecture.jsp";
			
			MyLectureDao dao = new MyLectureDao();
			ArrayList<MyLectureDto> list = dao.getMyLec(mid);
			
			req.setAttribute("list", list);
			
		}
	
		req.getRequestDispatcher(url).forward(req, resp);
	}
}
