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
		out.println("�⼮ �Է���...");

		StudentAttendanceInsertDao dao = new StudentAttendanceInsertDao();

		Enumeration params = req.getParameterNames(); // �Ķ���� ���
		System.out.println("----------------------------");
		while (params.hasMoreElements()){ // �Ķ���Ͱ� ���� ������
		    String name = (String)params.nextElement(); // �л���ȣ = �Ķ���� �̸�
		    System.out.println(name + " : " +req.getParameter(name)); // �л���ȣ : �⼮����
		    
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

		out.println("�⼮ �Է¿Ϸ�");
//		req.getRequestDispatcher("/admin_memberManagement/student_attendance_insert.jsp").forward(req, resp);
		resp.sendRedirect("/BEAT_LMS/student_attendance.lms");
		
	}

}
