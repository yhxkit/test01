package com.hb.day170915;
//서버 

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.reflect.Array;



public class InGameServer{

	//9001 포트 주시
	private static final int PORT = 9001; 
	private static ServerSocket listener;

	//클라이언트 닉네임 해시셋 => 새 클라이언트 들어올때 닉네임 중복되지 않게
	public static ArrayList<String> names = new ArrayList<String>();

	//클라이언트들의 프린트라이터 해시셋 => 한번에 돌려서 메세지 뿌리기 
	public static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

	public static HashMap<String, Integer> names_scores = new HashMap<String, Integer>();

	public static String roommaster = null;
	public static InQuiz q;


	InGameServer(){

		System.out.println("서버 스타트");

		try {
			listener = new ServerSocket(PORT); //서버소켓 생성
			while (true) {
				new Handler(listener.accept(), this).start(); //접속 들어오면 클라이언트 객체...스레드 생성 > 스레드 시작
				System.out.println("클라이언트 접속됨");

				q = new InQuiz();
				System.out.println("퀴즈 스레드 갱신");
			}
		} catch (IOException e) {e.printStackTrace();} 
		finally {
			try {
				listener.close();
			} catch (IOException e) {e.printStackTrace();} //서버소켓 닫음
		}

	}

	public static void main(String[] args) {

		new InGameServer(); 
		
	
	}


}

