package com.hb.day170915;

import java.io.*;
import java.util.*;
import java.net.Socket;



public class Handler extends Thread { 

	public static boolean resetpoint = false;
	int point=3;
	int score=0;

	boolean listen;

	private InGameServer server;

	private String name; //클라이언트 이름
	private Socket socket; //클라이언트 소켓

	private BufferedReader in; 
	private PrintWriter out;

	//생성자. 접속 소켓만 설정
	public Handler(Socket socket, InGameServer server) { 
		this.socket = socket;
		this.server = server;
	}//클라이언트 접속 소켓

	//중복되지 않은 닉네임 나올때까지 계속 창뜨고... 
	//닉네임 받고나면> 아웃풋스트림 등록 > 입력값받아서 뿌리기 반복
	public void run() {
		try {
			System.out.println("클라이언트 객체 생성됨");

			//소켓에서 쓸 문자 스트림 생성
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true); //오토플러시

			//클라이언트한테서 닉네임 받기. 중복되지 않은 이름받을때까지 계속 뜸
			while (true) {
				out.println("닉네임입력~~~"); //클라이언트에 닉네임입력하라고 명령줌

				name = in.readLine();

				if(name == null) {return;}//만약 닉네임치기 전에 창을 꺼버려서 null값이면 빠져나감
				if(name.trim().length()<1){continue;}//닉네임 비어있으면 못들어감

				synchronized (server.names) { //동기화 => 여러명의 클라이언트가 동시에 들어올 경우 names에서 임계영역=>셋 동기화로 잠금
					if (!server.names.contains(name)) { //이름이 중복되지 않아야 names 셋에 넣음
						server.names.add(name);
						if(server.names.size() == 1 ){server.roommaster = name;}

						System.out.println(server.names.size());

						break;
					}
				}


			}
			//이름이 정해졌으면 소켓 프린트 라이터를 셋에 넣어줌 => 전체 메세지 뿌리고 받아봐야 하니까
			server.writers.add(out); //out = 프린트라이터> writers 셋에 넣어줌

			/////////////////////////////////////입장시 접속자 리스트 갱신(입퇴장 반복 들어감-메서드로 안뺐음..)


			for (PrintWriter writer : server.writers) {
				writer.println("RECONNECTED ");
				writer.println("CONNECTED "+"접속자 수 > "+server.names.size());
			}
			for(String cntname : server.names){
				for (PrintWriter writer : server.writers) {
					if(cntname == server.roommaster){
						writer.println("CONNECTED " + "<방장>" + cntname);
					}else{
						writer.println("CONNECTED " + cntname);
					}
				}
			}
			/////////////////////////////////////////


			out.println("닉네임설정완료~~~"); //이름 정해짐 > 팝업
			System.out.println(name+" 입장");
			for (PrintWriter writer : server.writers) {
				writer.println("MESSAGE " + name + "님이 입장하셨습니다");
			}

			server.names_scores.put(name, score); //입장하면 랭크에 들어감



			//게임 시작 전에
			while(!server.q.isAlive()){

				String input = in.readLine();

				if (input == null) {
					return;
				}else if (input.equals("---방장이 게임을 시작했습니다---")){
					break;
				}else if(input.equals("STARTPRESSED")){
					if(name == server.roommaster){
						server.q.start();

						for (PrintWriter writer : server.writers){
							writer.println("GETSETREADY");
						}
						break;
					} else {continue;}
				}else{

					for (PrintWriter writer : server.writers){
						writer.println("MESSAGE " + name + ": " + input);
					}
				}

			}
			//게임시작!

			listen = server.q.isAlive();
			// 메세지 받아서 클라이언트 전체에 뿌리기	

			while (listen) {

				String input = in.readLine();
				String inp = input.replaceAll(" ", "").trim(); 


				// 입력하지 않은 다른 클라이언트들은 그냥 지나감.. 입력한 클라이언트 것만 뿌림
				if (input == null) {return;}



				if (input.equals("STARTPRESSED")){
					continue; //스타트 버튼 눌러도 아무일도 일어나지 않는다

				}else if(input.startsWith("MINUSONEP")){ 
					point=2;
					continue;
				}else if(input.startsWith("MINUSTWOP")){
					point=1;
					continue;
				}else if(input.startsWith("MINUSTHREEP")){
					point=0;
					continue;
				} else if(input.equals("POINTRESET")){
					point=3;
				} else if(input.equals("TOTHIRDPAGE")){ 

					if(name == server.roommaster){

						for(String name : server.names){
							for (PrintWriter writer : server.writers){
								writer.println("SCORE " + name +"의 점수("+ server.names_scores.get(name)+"점)");
							}
						}
					}
				} else {

					//입력받은 값을 이하와 같이 뿌려줌... MESSAGE 는 서브스트링 이용해서 클라이언트 측에서 처리
					for (PrintWriter writer : server.writers) {
						writer.println("MESSAGE " + name + ": " + input);}

					String ans = server.q.answer.trim().replaceAll(" ", "");


					if(inp.equalsIgnoreCase(ans)){  
						score += point;
						for (PrintWriter writer : server.writers) {
							writer.println("MESSAGE " + name + "님 정답 : " + input + "(" + point +"점)"); //문제자리에 뿌려줌
							writer.println("MESSAGE " + name + "님 총점 (" + score +"점)");
						}
						server.names_scores.replace(name, score);
						server.q.interrupt();	//인터럽트로 sleep상태에 익셉션 일으켜서 바로 다음 문제로 넘어감
						//							System.out.println("정답처리"+runnum);
						//							runnum++;	
					}	

				}

			}

		} catch (IOException e) {
			System.out.println(e);
			for (PrintWriter writer :server. writers) {
				writer.println("MESSAGE " + name + "님이 퇴장하셨습니다");
			}				
			System.out.println(name+" 퇴장");

		} finally {
			//클라이언트가 나갔으면 이름/프린트라이트를 셋에서 지우고 소켓 닫음
			if (name != null) {server.names.remove(name);}
			if (out != null) {server.writers.remove(out);}

			server.names_scores.remove(name); //나간 사람은 아예 랭크에서 삭제됨(원룸 구조라 방문자 리셋이 안되므로)

			/////////////////////////////////////퇴장시 접속자 리스트 갱신

			if(name == server.roommaster){server.roommaster = server.names.get(0);}

			for (PrintWriter writer : server.writers) {
				writer.println("RECONNECTED ");
				writer.println("CONNECTED "+"접속자 수 > "+ server.names.size());
			}
			for(String cntname : server.names){
				for (PrintWriter writer :server. writers) {
					if(cntname == server.roommaster){
						writer.println("CONNECTED " + "<방장>" + cntname);
					}else{
						writer.println("CONNECTED " + cntname);
					}
				}
			}

			/////////////////////////////////////////	

			try {
				socket.close();
			} catch (IOException e) {
			}
		}

	}
}
