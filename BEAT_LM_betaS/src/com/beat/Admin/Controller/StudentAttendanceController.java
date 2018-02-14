package com.beat.Admin.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beat.Admin.Model.StudentAttendanceDao;
import com.beat.Admin.Model.StudentAttendanceDto;

@WebServlet("/student_attendance.lms")
public class StudentAttendanceController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	protected void process (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("출석입력페이지 접속");
		
		StudentAttendanceDao dao = new StudentAttendanceDao();
		String selectColumn = null;
		if (req.getParameter("selectColumn") != null) {
			selectColumn = req.getParameter("selectColumn");
		}else {
			selectColumn="!@#$";
		}
		String searchText = null;
		if (req.getParameter("searchText") != null) {
			searchText = req.getParameter("searchText");
		}else {
			searchText="!@#$";
		}
		
		ArrayList<StudentAttendanceDto> list = dao.studentList(selectColumn, searchText);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/admin_memberManagement/student_attendance.jsp").forward(req, resp);
	}
}
