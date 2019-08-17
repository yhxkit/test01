package com.beat.Admin.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beat.Admin.Model.MemberDetailDao;
import com.beat.Admin.Model.MemberDetailDto;

@WebServlet("/memberDetail.lms")
public class MemberDetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getParameter("mnum")+"의 디테일페이지 접속");
		MemberDetailDao dao = new MemberDetailDao();
		String mnum = req.getParameter("mnum");
		ArrayList<MemberDetailDto> list = dao.memberListDetail(mnum);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/admin_memberManagement/memberDetail.jsp").forward(req, resp);
	}
}
