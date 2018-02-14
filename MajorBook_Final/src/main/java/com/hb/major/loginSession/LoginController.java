package com.hb.major.loginSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParser;
import com.hb.major.model.entity.UserVo;
import com.hb.major.service.checkMaster.CheckMasterService;
import com.hb.major.service.user.UserService;


@Controller
public class LoginController {
	

	@Autowired
	CheckMasterService checkMasterService; //권한 체크

	@Autowired
	private UserService userService; //우리쪽 db 계정 관리
	
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	 
	Main main = new Main();

	
	@RequestMapping(value = "/userdelete", method = RequestMethod.GET)
	public String unlinkPage() {
		System.out.println("앱 연결 해제 페이지 이동 (회원 탈퇴)");
		return "loginoutdel/userdelete";
	}
	
	@RequestMapping(value = "/userdelete", method = RequestMethod.DELETE)
	public String unlink(@ModelAttribute UserVo bean, HttpServletRequest req) {
		// db에서 아이디 삭제해야하니까 post방식으로
		String userid = (String)req.getSession().getAttribute("userid");
		System.out.println("탈퇴 들어옴"+userid);
		req.getSession().invalidate();
		System.out.println(userid);
		userService.userDeleteOne(userid);
		
		
		main.unlinkuser();//앱에서도 지움... 이거 프론트에서 처리가 잘못돼서 굳이 두단계로 나눴는데 백처리하면 필요 ㄴㄴ
		
		return "redirect:/";
	}
	
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req ) {
		
		System.out.println("로갓들어옴~"); 
	
		
		HttpSession session = req.getSession();
		session.invalidate(); //세션 없앰
		main.logout(); //앱에서도 로갓
			
		return "redirect:/";
	}
	
	
	
	@RequestMapping(value = "/kauth", method = RequestMethod.GET) //오 코드받기 성공했어 ㅠㅠ 
	public String login(Locale locale, Model model, RedirectAttributes redirectAttribute, HttpServletRequest req , HttpServletResponse res) {
		
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(30*60*1); //1시간 동안 세션 처리함 
		
		
		String code= req.getParameter("code"); //kakao에서 로그인시 url 값으로 넘겨받은 권한
		
		System.out.println("로그인버튼과 연동해야겠네? authorization 코드머냐 "+code);
		getaccesstoken(code, redirectAttribute, session);
		
	
		return "redirect:/";
		
	}
	
	
	public void getaccesstoken(String code, RedirectAttributes redirectAttribute, HttpSession session) {
		
	final String AUTH_HOST = "https://kauth.kakao.com";
    final String tokenRequestUrl = AUTH_HOST + "/oauth/token";

    String CLIENT_ID = "6f1f5ce92db4949ae9395f384a2366f5"; // 해당 앱의 REST API KEY 정보. 개발자 웹사이트의 대쉬보드에서 확인 가능
    String REDIRECT_URI = "http://203.236.209.213:8080/major/kauth"; // 해당 앱의 설정된 uri. 개발자 웹사이트의 대쉬보드에서 확인 및 설정 가능
    String getcode =  code;
    
	logger.info(code);
    
    
    HttpsURLConnection conn = null;
    OutputStreamWriter writer = null;
    BufferedReader reader = null;
    InputStreamReader isr = null; 

    try {
      final String params = String.format("grant_type=authorization_code&client_id=%s&redirect_uri=%s&code=%s",
                        CLIENT_ID, REDIRECT_URI, code);

      final URL url = new URL(tokenRequestUrl);

      conn = (HttpsURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setDoOutput(true);

      writer = new OutputStreamWriter(conn.getOutputStream());
      writer.write(params);
      writer.flush();

      final int responseCode = conn.getResponseCode();
      System.out.println("\nSending 'POST' request to URL : " + tokenRequestUrl);
      System.out.println("Post parameters : " + params);
      System.out.println("Response Code : " + responseCode);

      isr = new InputStreamReader(conn.getInputStream());
      reader = new BufferedReader(isr);
      final StringBuffer buffer = new StringBuffer();
      String line;
      while ((line = reader.readLine()) != null) {
        buffer.append(line);
      }

      System.out.println("이거야????여기서 액세스코드 받으면 됨?"+buffer.toString());
      /////
     JSONObject jsonobj  = new JSONObject(buffer.toString());
     
     String at =jsonobj.get("access_token").toString();
     String rfsat =jsonobj.get("refresh_token").toString();
     String exp =jsonobj.get("expires_in").toString();
     String rfsexp =jsonobj.get("refresh_token_expires_in").toString();
     
      System.out.println("빼낸 엑세스토큰"+at);
      System.out.println("빼낸 리프레시토큰"+rfsat); 
      System.out.println("빼낸 액세스토큰만료시간"+exp);
      System.out.println("빼낸 리프레시만료시간"+rfsexp); 
      
     ///////////
    //  Main main = new Main();
      main.test(at); //액세스 토큰 넣고..
      
      System.out.println("string 받아욤"+main.login());
      
      JSONObject jsonobj2  = new JSONObject(main.login());
      String userid = jsonobj2.get("id").toString();
      
      JSONObject jsonobj3 =new JSONObject(jsonobj2.get("properties").toString()); 
      String usernick = jsonobj3.get("nickname").toString();
      String userthumb = jsonobj3.get("thumbnail_image").toString();
      String userprofile = jsonobj3.get("profile_image").toString();
      
      System.out.println(userid+":"+usernick);
      
    //  redirectAttribute.addFlashAttribute("userid", userid);
     // redirectAttribute.addFlashAttribute("usernick", usernick);
      //가끔 왜 이 모델 값이 파라미터로 주소줄에 표시되는지 모르겠음;
      
      
      try {
		boolean checkresult= checkMasterService.checkMaster(userid);
		session.setAttribute("mastercheck", checkresult);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
      
      session.setAttribute("userid", userid);
      session.setAttribute("usernick", usernick);
      
      /////
      UserVo bean = new UserVo();
      
      bean.setKakao_id(userid);
      bean.setKakao_nick(usernick);
      bean.setKakao_profile_image(userprofile);
      bean.setKakao_thumbnail_image(userthumb);
      
  	 userService.userCheck(bean);
  	 /////
      
      
      

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
        // clear resources
        if (writer != null) {
          try {
              writer.close();
           } catch(Exception ignore) {
           }
        }
        if (reader != null) {
          try {
              reader.close();
          } catch(Exception ignore) {
          }
        }
        if (isr != null) {
            try {
                isr.close();
            } catch(Exception ignore) {
            }
         }
    }
	

	
	}	
	

	
}
