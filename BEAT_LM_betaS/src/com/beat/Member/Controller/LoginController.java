package com.beat.Member.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RespectBinding;

import com.beat.Member.model.LoginDao;
import com.beat.Member.model.LoginDto;

@WebServlet("/login.lms")
public class LoginController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
		
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String mid = req.getParameter("mid");
		String mpw = req.getParameter("mpw");
		String snum = req.getParameter("radioName");
		
		
		//System.out.println(snum+"���� ��������?");
	
		
		String sendTo = "";
		
		LoginDao loginDao = new LoginDao();
		ArrayList<LoginDto> list = loginDao.memberLogin(mid, mpw, snum); //�α��� ó�� 
		HttpSession session = req.getSession(); 
		
			
			if(list.get(0).getloginResult()==true){ //�α��� ������ �������� �Ѿ
				
				
				//dao���� snum�˻� ó�� �Ϸ�
				
				if(snum.equals("1")){ //�Ϲ� ȸ�� 
					sendTo ="/index/index_post.jsp";
					
				}else if(snum.equals("2")){//����
					sendTo ="/index/adindex.jsp";
				}
				
				
				
				req.setAttribute("loginChk", true);				
				
				session.setAttribute("result", true);
				session.setAttribute("mid", list.get(0).getMid()); //������ ���ǿ� ���̵� �־���
				session.setAttribute("snum", snum); //������ ���ǿ� snum �־���
				
				session.setMaxInactiveInterval(30*60);
				
				
			}else{ //�α��� ���н� �ٽ� �α��� â����
				
				sendTo = "/login/login.jsp";
				
				req.setAttribute("loginChk", false);
				session.setAttribute("result", false);
			}
			
		req.getRequestDispatcher(sendTo).forward(req, resp);
		
	}
	
}