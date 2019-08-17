package com.beat.Admin.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beat.Admin.Model.StudentAttendanceInsertDao;

@WebServlet("/student_attendance_insert.lms")
public class StudentAttendanceInsertController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	ArrayList<Integer> presentDays = new ArrayList<Integer>();
	ArrayList<Integer> absentDays = new ArrayList<Integer>();
	ArrayList<Integer> lateTimes = new ArrayList<Integer>();
	ArrayList<Integer> earlyHome = new ArrayList<Integer>();

	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("출석 입력중...");

		StudentAttendanceInsertDao dao = new StudentAttendanceInsertDao();

		Enumeration params = req.getParameterNames(); // 파라미터 목록
		System.out.println("----------------------------");
		while (params.hasMoreElements()){ // 파라미터가 값을 가지면
		    String name = (String)params.nextElement(); // 학생번호 = 파라미터 이름
		    System.out.println(name + " : " +req.getParameter(name)); // 학생번호 : 출석상태
		    
		    if(req.getParameter(name).equals("presentDays")) presentDays.add(Integer.parseInt(name));
		    if(req.getParameter(name).equals("absentDays")) absentDays.add(Integer.parseInt(name));
		    if(req.getParameter(name).equals("lateTimes")) lateTimes.add(Integer.parseInt(name));
		    if(req.getParameter(name).equals("earlyHome")) earlyHome.add(Integer.parseInt(name));
		}
		System.out.println("----------------------------");
		
		dao.studentAttendanceInsert(presentDays, "presentDays");
		dao.studentAttendanceInsert(absentDays, "absentDays");
		dao.studentAttendanceInsert(lateTimes, "lateTimes");
		dao.studentAttendanceInsert(earlyHome, "earlyHome");

		out.println("출석 입력완료");
//		req.getRequestDispatcher("/admin_memberManagement/student_attendance_insert.jsp").forward(req, resp);
		resp.sendRedirect("/BEAT_LMS/student_attendance.lms");
		
	}

}
