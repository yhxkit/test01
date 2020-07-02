package com.beat.Notice.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beat.Notice.model.LMSNoticeDao;
import com.beat.Notice.model.LMSNoticeDto;

@WebServlet("/notice_detail.lms")
public class NoticeDetailController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		LMSNoticeDao lmsNoticeDao = new LMSNoticeDao();
		System.out.println("�Դ�");
		int lmsblog = Integer.parseInt(req.getParameter("lmsblog"));
		System.out.println(lmsblog);
		
		LMSNoticeDto bean = lmsNoticeDao.selectOne(lmsblog);
		
		req.setAttribute("noticeDetail", bean);
		
		// �л���� - row Ŭ�� - detail ������ �̵�
		req.getRequestDispatcher("/notice/notice_detail.jsp").forward(req, resp);
	}
}