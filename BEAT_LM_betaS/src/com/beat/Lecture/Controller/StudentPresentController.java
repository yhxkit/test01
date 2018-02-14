package com.beat.Lecture.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beat.Lecture.model.StudentPresentDao;
import com.beat.Lecture.model.StudentPresentDto_john;

@WebServlet("/studentadminnnn.lms")
public class StudentPresentController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		System.out.print("aaa");		
		StudentPresentDao dao=new StudentPresentDao();
		ArrayList<StudentPresentDto_john> list = dao.studentPresentList();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/admin_memberManagement/memberList.jsp").forward(req, resp);
		
		
	}
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//	}
}
