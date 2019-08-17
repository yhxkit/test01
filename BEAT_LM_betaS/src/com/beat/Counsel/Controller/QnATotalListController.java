package com.beat.Counsel.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beat.Counsel.Model.QnaDao;
import com.beat.Counsel.Model.QnaDto;

@WebServlet("/QnaTotal.lms")
public class QnATotalListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String pageString=req.getParameter("page");
		int page=Integer.parseInt(pageString);
		QnaDao dao = new QnaDao();
		ArrayList<QnaDto> list = dao.qnalist();
		ArrayList<QnaDto> listpaging = new ArrayList<QnaDto>();
		
		if ((page) * 10 < list.size()) {
			for (int i = (page - 1) * 10; i < (page) * 10; i++) {
				listpaging.add(list.get(i));				
			}
			req.setAttribute("list", list);
			req.setAttribute("listpaging", listpaging);			
			req.setAttribute("totalPages", list.size()/10+1);
			req.getRequestDispatcher("/contactus/Q&A.jsp?page="+page).forward(req, resp);
			
		} else {
			for (int i = (page - 1) * 10; i<list.size(); i++) {
				listpaging.add(list.get(i));
			}
			req.setAttribute("listpaging", listpaging);
			req.setAttribute("list", list);
			req.setAttribute("page", page);
			req.getRequestDispatcher("/contactus/Q&A.jsp?page="+page).forward(req, resp);
		}
		
		
	}
}
