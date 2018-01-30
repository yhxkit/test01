package com.hb.major.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hb.major.service.checkMaster.CheckMasterService;



@Controller
public class CheckMasterController {

	@Autowired
	CheckMasterService checkMasterService;
	

	@RequestMapping(value = "/checkmasteraccount", method = RequestMethod.POST)
	public void checkmaster(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");

		String checkid=req.getParameter("checkid");
		System.out.println("관리자 체크 시작합니다"+checkid);
		resp.getWriter().write(getJSON(checkid));
		
	}
	
	public String getJSON(String mastercheckid) {
		
		
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":");
		
		try {
			

		boolean checkresult= checkMasterService.checkMaster(mastercheckid);
		
		result.append("{\"value\":\""+checkresult+"\"}");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			result.append("}");
		
		
		return result.toString();
	}
	
	
	
}
